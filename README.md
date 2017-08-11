# RICHdocs
### Current data structure format
Currenly have raw tdc data bank **[RAW::TDC]** because I have not implemented RICH mapping and translation tables yet. We **won't** have [RAW::TDC] after the translation is implemented.

| raw EVIO | RAW::TDC ||
|----------|--------------|---|
| crate[84] | crate[84] [8bit]
| slot[3:7] [8bit] | slot[3:7] [8bit]
| fiber[0:31] [8bit] | channel[0:6143] [16bit] | `channel=fiber*192+chan`
| chan[0:191] [8bit] | TDC [32bit] | edge and TDC [15bit] from raw EVIO is packed into TDC [32bit]
| edge[0:1] [1bit]
| TDC [15bit]

### Future (final?) data structure format

| raw EVIO | decoded HIPO ||
|----------|--------------|---|
| crate[84] | sector [8bit] | slots [3:7] are assigned to sector 2
| slot[3:7] [8bit] | layer[138] [8bit] | `layer=board`, `[115 3xMAROC]+[23 2xMAROC]=138 boards` fit into 8bit word
| fiber[0:31] [8bit] | component[0:191] [16bit] | `component=MAROC channel`, 192 or 128 channels fit into 16 bit word
| channel[0:191] [8bit] | order[0:1] [8bit] | `order=edge`, 2 possible values LEADING/TRAILING
| edge[0:1] [1bit] | TDC [32bit] | for RICH data only 15 bits are effectively occupied
| TDC [15bit]
