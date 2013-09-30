Liste der ben�tigten Klassen
============================

Lager
-----
Diese Klassen definieren das Lager und seine Teile

Location (Lagerort)
   - der Lagerort beinhaltet eine bis mehrere Gassen

Gap (Gasse)
   - die Gasse beinhaltet ein linkes und / oder ein rechte Grid
   - die Gasse hat eine eindeutige Nummer (aufsteigend)

Grid (Gestell)
- das Grid hat eine beliebige Anzahl Reihen und Spalten
- das Grid hat eine H�he und eine L�nge (abh�ngig von Breite der Spalten und H�he der Reihen)

Column (Spalte)
- die Spalte hat eine Breite
- die Spalte hat eine Nummer (aufsteigend)

Row (Reihe)
- die Reihe hat eine H�he
- die Reihe hat eine Nummer (aufsteigend)

Bin (Lagerplatz)
- der Lagerplatz liegt in einer Spalte und in einer Reihe
- die Koordinaten des Lagerplatzes werden anhand der Spalte und Reihe berechnet (im Konstruktor) (vorhergehende Spaltenbreiten summiert = X; vorhergehende Reihenh�hen summiert = Z)
- der Lagerplatz hat einen Belegungszustand [Frei, Belegt, Reserviert, Defekt usw.]
- der Lagerplatz ist einer Kategorie zugeordnet (dieselbe Referenz wie bei Product) [Normal, Heavy, Liquid, Explosive usw.]

Produkte
--------
Diese Klassen definieren die Produkte, welche eingelagert werden

Product (Artikel)
- der Artikel ist einer Kategorie zugeordnet (dieselbe Referenz wie bei Lagerplatz) [Normal, Heavy, Liquid, Explosive usw.]

Category (Kategorie)
- die Kategorie hat eine Namen [Normal, Heavy, Liquid, Explosive usw.]

Berechnungen/Simulation
-----------------------
Diese Klassen sind f�r die Optimierung, Berechnung der k�rzesten Wege usw.

- tbd


GUI
---
Diese Klassen sind f�r die Oberfl�che (GUI)

- tbd


Import/Export
-------------
Diese Klassen sind f�r den Import / Export der Lagerorte (zum Laden einer bestehenden Konfiguration, zum Speichern einer berechneten Konfiguration)

LocationImport
``````````````

- LocationImportASCII
- LocationImportCSV
- LocationImportSQL

LocationExport
``````````````

- LocationExportASCII
- LocationExportCSV
- LocationExportSQL

