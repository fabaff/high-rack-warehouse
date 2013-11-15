Work packages 15. November 2013
===============================

1. Definition für Lagerort (Generierung der Lager-Struktur)
2. Liste mit Lagergütern (Artikelliste)
3. Liste für die Belgung eines Lagerort (Belegungsliste) bei bereits gefüllten
   Lagerorten
4. Liste für Operationen (Auftragsliste)
 
Lager-Struktur
--------------
Elemente: Anzahl Gassen, Wände (Grids)

Artikelliste
------------
Elemente: Artikel-Nr., Artikel-Bezeichnung, Type (optional)

Belegungsliste
--------------
Elemente: Artikel-Nr., Bin-Nr. (unique)


Auftragsliste
-------------
Elemente: Timestamp, Art der Operation (Einlagerung, Auslagerung, Umlagerung),
Artikel-Nr., Quellen-Bin-Nr. (opt), Ziel-Bin-Nr. (opt)

XXXXXXXXX, in, X12345, [optionale Elemente]
