Besprechung 18.10.2013
=======================


- Es muss eine Lagerzugriffsliste geladen werden k�nnen:
 -> Automatisch erstellt per Zufallsgenerator (Gauss, poison?)
 Verteilung sollte der realen Verteilung entsprechen, regelt Intervalle, H�ufigkeit, Regelung, Ablauf

 Online STrategie f�r Platzierung (Optimierung) -> Stack, Priority Queue --> Fortschritt der Simulation!
-> Einlagerung (Greedy verfahren)
-> Simulation: Eventbasiert (discrete event driven simulator).. Rundenbasiert macht wenig sinn..
=> Siehe PDF Simulation_Systems.pdf



=> discrete event driven simulator = schreitet voran wenn ein ereigniss auftritt: ein oder auslagerung von Produkten..
- Simulation Engine verarbeitet die Ereignisse und �berwacht den fortschritt der Simulation..
- Spr�nge sind zul�ssig / Detailierungsgrad im Auge behalten)
- Grafik Engine zeigt die Simulation an (muss gentrennt sein von der simulations engine!)
- Schalter damit die geswchwindigkeit der Simulation angepasst werden kann (Realtime bis AsFastAsPossible)


- Erstellen einer Schlussdokumentation

Granularit�t (Moderllierung / Simulation)