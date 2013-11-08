Events
=======================

Global

	StoreEvent
		1 Get next free Gap
		2 Select Gap (first to be free?)
		3.1 Assign Item to Bin
		3.2 Send Item to Gap
		3.3 Set RBG to 0.0
		4 Load Item to RBG
		5 Store Item in Bin
		
	
		
	


Zustände
	pro Gasse
	RBG Einlagern
		Eventnr.	Koord.	Zustand
		1 			0/0 	empty
		2 			0/0 	loaded
		3 			y/z 	loaded
		4 			x 		loaded
		5 			x 		empty
		6 			y/z 	empty
		1			0/0		empty
		7 					<sleep>
	
	RBG Auslagern
		Eventnr.	Koord.	Zustand
		1 			0/0 	empty
		6 			y/z 	empty
		5 			x 		empty
		4 			x 		loaded
		3 			y/z 	loaded
		2 			0/0 	loaded
		1			0/0		empty
		7 					<sleep>

		