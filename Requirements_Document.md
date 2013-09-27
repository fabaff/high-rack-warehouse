Anforderungsdokument

Vorwort
------------------

Benutzeranforderungen
------------------
Funktionale Anforderungen
- Definition des Szenarios (Statische Parameter)
- Eingeben der Simulationsparameter (dynamische Parameter)
- Simulationssteuerung
- Szenarienmanagement
- 



Nichtfunktionale Anforderungen
------------------
- Keine unsinnig grossen (langlaufende) Simulationen
- Grafische Darstellung während der Simulation (informativ)
- Sprache der Applikation ist in Englisch
- Ausgabe / Export der Ergebnisse auf Drucker oder als Dokument (z.B. .txt, .csv, .xml usw.)



Domainspezifische Anforderungen
------------------
- Gefahrengut / Brandschutz
- Konformität
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
- 



Nichtfunktionale Anforderungen
------------------
- Lauffähig auf Standard-Hardware
- 
- 
- 


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
- Maschinenbaufirma im 1-Schichtbetrieb mit Fertigung / Montage / Service --> kurze Zugriffszeiten Tagsüber, freie Ressourcen während der Nacht
- Versandhandel im 3-Schichtbetrieb mit Bereitstellung / Konvektionierung --> hoher Lagerdurchsatz, 24h-Zugriff für Ein-/Auslagerung
- Gleichzeitiges Ein-/Auslagern, Queue
- Mehrere Ein-/Ausgabeplätze pro Gasse (auf Z-Achse)
- Mehrere Regalbediengeräte pro Gasse (auf X-Achse, bei mehreren Ein-/Ausgabeplätzen auch auf Z-Achse)
- Mehrere Ladearme pro Regalbediengerät (mehrere Vertikal, Horizontal [ohne / mit Durchreichemöglichkeit], Radial)
- Vorgezogenes Auslagern (Bereitstellung noch im Lager)
- Aufall einer Gasse, Mehrplatzeinlagerung gleicher Teile in unterschiedlichen Gassen
- Fixe Lagerplatzzuordnung (Reservation, defekte Lagerplätze, abgeschottete Lagerplätze für Gefahrengut)
- Befüllung (inital von leerem Lager / Nachbefüllung) (zufällig chaotisch, zeitoptimiert chaotisch, zugeordnet, Positionsoptimiert [ABC])
- Optimierung von bereits belegtem Lager aufgrund Zugriff-History
- 
- 
- 

Parameter
------------------
- Lagergut (Materielle Eigenschaften wie Fest, flüssig, Fragile, Gefahrengut [Brennbar, Chemie, Explosiv] + Physikalische Eigenschaften wie Gewicht, Schwerpunkt, Standfestigkeit)
- Regalbediengerät (Fahrdynamik)
- Gebäudegeometrie
- Ein-/Auslagerungsaufträge (als Liste in das Programm zu laden [zufällig generierte Liste])
- 
- 

