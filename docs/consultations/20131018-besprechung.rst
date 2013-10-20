Besprechung 18.10.2013
=======================

- Es muss eine Lagerzugriffsliste geladen werden können:
 - Automatisch erstellt per Zufallsgenerator (Gauss-, Poisson-Verteilung)
 - Verteilung sollte der realen Verteilung entsprechen, regelt Intervalle, Häufigkeit, Regelung, Ablauf

- Online-Strategie für Platzierung (Optimierung) -> Stack, Priority Queue --> Fortschritt der Simulation!
  - Einlagerung (Greedy-Verfahren)
  - Simulation: Eventbasiert (discrete event driven simulator). Rundenbasiert macht wenig Sinn.

Parallel and Distributed Simulation Systems
Richard M. Fujimoto
JOHN WILEY & SONS, INC.

discrete event driven simulator = schreitet voran, wenn ein Ereigniss auftritt: Ein- oder Auslagerung von Produkten.

- Simulation Engine verarbeitet die Ereignisse und überwacht den fortschritt der Simulation.
- Sprünge sind zulässig / Detailierungsgrad im Auge behalten)
- Grafik-Engine zeigt die Simulation an (muss gentrennt sein von der Simulations Engine!)
- Schalter, damit die Geschwindigkeit der Simulation angepasst werden kann (Realtime bis AsFastAsPossible)
- Erstellen einer Schlussdokumentation

**Granularität (Modellierung / Simulation)**
