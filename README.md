# RICHdocs
### Current data structure format
Currenly have raw tdc data bank **[RAW::TDC]** because I have not implemented RICH mapping and translation tables yet:

| raw EVIO | RAW::TDC ||
|----------|--------------|---|
| crate | crate [8bit]
| slot [8bit] | slot [8bit]
| fiber [8bit] | channel [16bit] | `channel=fiber*192+chan`
| chan [8bit] | TD C[32bit] | edge and TDC [15bit] from raw EVIO is packed into TDC [32bit]
| edge [1bit]
| TDC [15bit]

### Future (final?) data structure format

| raw EVIO | decoded HIPO ||
|----------|--------------|---|
| crate | sector [8bit] | slots [0:7] are assigned to sector 2
| slot [8bit] | layer [8bit] | `layer=board`, 1138 boards fit into 8bit word
| fiber [8bit] | component [16bit] | `component=MAROC channel`, 192 or 128 channels fit into 16 bit word
| channel [8bit] | order [8bit] |
| edge [1bit] | TDC [32bit] |
| TDC [15bit]
