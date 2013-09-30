Liste der benötigten Klassen
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
- das Grid hat eine Höhe und eine Länge (abhängig von Breite der Spalten und Höhe der Reihen)

Column (Spalte)
- die Spalte hat eine Breite
- die Spalte hat eine Nummer (aufsteigend)

Row (Reihe)
- die Reihe hat eine Höhe
- die Reihe hat eine Nummer (aufsteigend)

Bin (Lagerplatz)
- der Lagerplatz liegt in einer Spalte und in einer Reihe
- die Koordinaten des Lagerplatzes werden anhand der Spalte und Reihe berechnet (im Konstruktor) (vorhergehende Spaltenbreiten summiert = X; vorhergehende Reihenhöhen summiert = Z)
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
Diese Klassen sind für die Optimierung, Berechnung der kürzesten Wege usw.

- tbd


GUI
---
Diese Klassen sind für die Oberfläche (GUI)

- tbd


Import/Export
-------------
Diese Klassen sind für den Import / Export der Lagerorte (zum Laden einer bestehenden Konfiguration, zum Speichern einer berechneten Konfiguration)

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

