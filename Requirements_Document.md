Anforderungsdokument

Vorwort
------------------

Benutzeranforderungen
------------------



Funktionale Anforderungen
------------------
- Definition des Szenarios (Statische Parameter)
- Eingeben der Simulationsparameter (dynamische Parameter)
- Simulationssteuerung
- Szenarienmanagement



Nichtfunktionale Anforderungen
------------------
- Keine unsinnig grossen (langlaufende) Simulationen
- Grafische Darstellung w�hrend der Simulation (informativ)
- Sprache der Applikation ist in Englisch
- Ausgabe / Export der Ergebnisse auf Drucker oder als Dokument (z.B. .txt, .csv, .xml usw.)



Domainspezifische Anforderungen
------------------
- Gefahrengut / Brandschutz
- Konformit�t
- Arbeitssicherheit



System-Architektur
------------------
- Clientanwendung
- Trennung von Simulation, Auswertung und Visualisierung


Systemanforderungen
------------------

Funktionale Anforderungen
- Szenario laden
- Szenario simulieren / berechnen
- Simuliertes Szenario auswerten / ausgeben



Nichtfunktionale Anforderungen
------------------
- Lauff�hig auf Standard-Hardware


System-Evolution
------------------
N/A

Testing
------------------
Unit Tests

Anhang
------------------

Szenarien
------------------
- Maschinenbaufirma im 1-Schichtbetrieb mit Fertigung / Montage / Service --> kurze Zugriffszeiten Tags�ber, freie Ressourcen w�hrend der Nacht
- Versandhandel im 3-Schichtbetrieb mit Bereitstellung / Konvektionierung --> hoher Lagerdurchsatz, 24h-Zugriff f�r Ein-/Auslagerung
- Gleichzeitiges Ein-/Auslagern, Queue
- Mehrere Ein-/Ausgabepl�tze pro Gasse (auf Z-Achse)
- Mehrere Regalbedienger�te pro Gasse (auf X-Achse, bei mehreren Ein-/Ausgabepl�tzen auch auf Z-Achse)
- Mehrere Ladearme pro Regalbedienger�t (mehrere Vertikal, Horizontal [ohne / mit Durchreichem�glichkeit], Radial)
- Vorgezogenes Auslagern (Bereitstellung noch im Lager)
- Aufall einer Gasse, Mehrplatzeinlagerung gleicher Teile in unterschiedlichen Gassen
- Fixe Lagerplatzzuordnung (Reservation, defekte Lagerpl�tze, abgeschottete Lagerpl�tze f�r Gefahrengut)
- Bef�llung (inital von leerem Lager / Nachbef�llung) (zuf�llig chaotisch, zeitoptimiert chaotisch, zugeordnet, Positionsoptimiert [ABC])
- Optimierung von bereits belegtem Lager aufgrund Zugriff-History

Parameter
------------------
- Lagergut (Materielle Eigenschaften wie Fest, fl�ssig, Fragile, Gefahrengut [Brennbar, Chemie, Explosiv] + Physikalische Eigenschaften wie Gewicht, Schwerpunkt, Standfestigkeit)
- Regalbedienger�t (Fahrdynamik)
- Geb�udegeometrie
- Ein-/Auslagerungsauftr�ge (als Liste in das Programm zu laden [zuf�llig generierte Liste])

