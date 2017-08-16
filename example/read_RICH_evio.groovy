#!/usr/bin/groovy
import org.jlab.detector.decode.CLASDecoder
import org.jlab.io.evio.EvioDataEvent
import org.jlab.io.evio.EvioSource
import org.jlab.groot.data.H2F
import org.jlab.groot.ui.TCanvas


def h2 = new H2F("h2","fiber vs channel",192,0,192,32,0,32);
def decoder = new CLASDecoder(false)


EvioSource reader = new EvioSource()
reader.open(args[0])

while(reader.hasEvent() == true){
     EvioDataEvent event = (EvioDataEvent) reader.getNextEvent()
	def hipoEv = decoder.getDataEvent(event)

	if(hipoEv.hasBank("RAW::tdc")){
		def fbank = hipoEv.getBank("RAW::tdc")
		for(int itdc=0; itdc < fbank.rows(); itdc++){                                                                                                   
			def crate = fbank.getByte("crate", itdc)
			def slot = fbank.getByte("slot", itdc)
			def channel = fbank.getShort("channel", itdc)

			int fiber = channel/192
			int chan = channel%192

			short rawtdc = fbank.getInt("TDC", itdc)
			int edge = (rawtdc>>15)&0x1
			int tdc = rawtdc&0x7FFF

//			println crate+" "+slot+" "+fiber+" "+chan+" "+edge+" "+tdc
			h2.fill(chan,fiber)
		}
	}
}

reader.close()

h2.setTitleX("MAROC channels")
h2.setTitleY("FIBER number")
TCanvas can = new TCanvas("can", 1280, 1024)
can.draw(h2)

