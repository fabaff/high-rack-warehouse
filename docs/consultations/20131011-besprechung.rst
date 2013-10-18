Besprechung 11.10.2013
======================

System-Anforderungen
--------------------

- Realtime Simulation! aber variabel in beide Richtungen
  - 1 Minute in der Simulation muss 1 Minute in Echtzeit sein
- Es muss eine Lagerzugriffsliste geladen werden k�nnen::

    Zeit        Typ         Artikel     Lagerplatz
    ----------------------------------------------------------
    07:05:13	Auslagerung A
    07:12:59	Auslagerung F
    08:13:24	Einlagerung	C
    09:12:09	Umlagerung	D           V62 --> c34
    ...		...		...		...
    usw.

- Es sollen Lagerzugriffe w�hrend der Simulation eingegeben werden k�nnen

- Plausibilit�tspr�fung nur f�r physikalische Parameter, NICHT aber f�r die Laufzeit!
- Plausibilit�tspr�fung f�r den auszuf�hrenden Rechner betreffend RAM, CPU usw.
