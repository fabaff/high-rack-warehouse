Besprechung 01.11.2013
=======================


- Prozesse festlegen
- Ereignisse definieren:
	- Ware trifft ein (vorg�ngig festgelegter Zeitpunkt, programm reagiert aber erst darauf wenn eintritt) Programm geht nur vom IST zustand aus.
	- Ware wird richtigung GAP verschoben (wird nun bereits der Arm zum Abholpunkt verschoben?)
	- Ware ist in GAP
	- Ware wird zu BIN verschoben
	- Ware ist vor BIN
	- Ware wird eingelagert in BIN
Wenn Ware eintrifft, in welcher Reihenhoflge werden Events ausgef�hrt (z.B. Arm bewegt sich bereits zur Aufhname, w�hrend Ware vom Eingang zum GAP Verschoben wird)
- Handling von gleichzeitig eintreffenden Waren (Events)
- Paralellit�t der Gassen
	
M�gliche Events:
- Ware wird angenommen
- Ware wird zum GAP verschoben
- Arm bewegt sich (mit Ware, ohne Ware, vom Annahme nach Bin, von Bin nach annahme)
- Ware ist auf Arm
- Ware wird in BIN eingelagert / ausgelagert


Zentraler Eventhandler f�r Opitmierung wohl geeigneter als viele einzelne Eventhandler.

/TODO
Modell erstellen
Datenstrucktur festlegen (Zustandsverwaltung usw)
Eventhandler bestimmen (oder Agenten / Master-System)