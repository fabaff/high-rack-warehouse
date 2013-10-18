Besprechung 18.10.2013
=======================


- Es muss eine Lagerzugriffsliste geladen werden können:
 -> Automatisch erstellt per Zufallsgenerator (Gauss, poison?)
 Verteilung sollte der realen Verteilung entsprechen, regelt Intervalle, Häufigkeit, Regelung, Ablauf

 Online STrategie für Platzierung (Optimierung) -> Stack, Priority Queue --> Fortschritt der Simulation!
-> Einlagerung (Greedy verfahren)
-> Simulation: Eventbasiert (discrete event driven simulator).. Rundenbasiert macht wenig sinn..
=> Siehe PDF Simulation_Systems.pdf



=> discrete event driven simulator = schreitet voran wenn ein ereigniss auftritt: ein oder auslagerung von Produkten..
- Simulation Engine verarbeitet die Ereignisse und überwacht den fortschritt der Simulation..
- Sprünge sind zulässig / Detailierungsgrad im Auge behalten)
- Grafik Engine zeigt die Simulation an (muss gentrennt sein von der simulations engine!)
- Schalter damit die geswchwindigkeit der Simulation angepasst werden kann (Realtime bis AsFastAsPossible)


- Erstellen einer Schlussdokumentation

Granularität (Moderllierung / Simulation)