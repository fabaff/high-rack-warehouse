Besprechung 18.10.2013
=======================

- Es muss eine Lagerzugriffsliste geladen werden k�nnen:
 - Automatisch erstellt per Zufallsgenerator (Gauss-, Poisson-Verteilung)
 - Verteilung sollte der realen Verteilung entsprechen, regelt Intervalle, H�ufigkeit, Regelung, Ablauf

- Online-Strategie f�r Platzierung (Optimierung) -> Stack, Priority Queue --> Fortschritt der Simulation!
  - Einlagerung (Greedy-Verfahren)
  - Simulation: Eventbasiert (discrete event driven simulator). Rundenbasiert macht wenig Sinn.

Parallel and Distributed Simulation Systems
Richard M. Fujimoto
JOHN WILEY & SONS, INC.

discrete event driven simulator = schreitet voran, wenn ein Ereigniss auftritt: Ein- oder Auslagerung von Produkten.

- Simulation Engine verarbeitet die Ereignisse und �berwacht den fortschritt der Simulation.
- Spr�nge sind zul�ssig / Detailierungsgrad im Auge behalten)
- Grafik-Engine zeigt die Simulation an (muss gentrennt sein von der Simulations Engine!)
- Schalter, damit die Geschwindigkeit der Simulation angepasst werden kann (Realtime bis AsFastAsPossible)
- Erstellen einer Schlussdokumentation

**Granularit�t (Modellierung / Simulation)**
