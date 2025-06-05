### Äquivalenzklassenanalyse

| # | Bedingung                                 | gÄK                                                               | uÄK                               |
|---|-------------------------------------------|-------------------------------------------------------------------|-----------------------------------|
| 1 | Fahrradtypen                              | RACE, FIXIE, SINGLE_SPEED                                         | GRAVEL, EBIKE                     |
| 2 | Kunde hat keinen weiteren offenen Auftrag | Kunde hat einen einzigen Auftrag | Kunde hat nicht nur einen Auftrag |
| 3 | Anzahl offener Aufträge im Shop           | 0 bis 4 Aufträge                                                  | 5 oder mehr Aufträge              |


### Grenzwertanalyse

| Testnummer          | 1   | 2        | 2              |
|---------------------|-----|----------|----------------|
| geprüfte(r) ÄK/GW   | gÄK1u | gÄK1o    | uÄK2u          |
| x                   | 0   | 4        | 5              |
| Erwartetes Ergebnis | return 'true'     | return 'true' | return 'false' |

 ### konkreten Testfällen

| Test | Fahrradtypen   | hat der Kunde weitere offene Aufträge ? | Anzahl Aufträge im Shop | erwaterte Ergebnisse |
|------|-----|-----------------------------------------|-------------------------|----------------------|
| 1    | RACE | nein                                    | 0                       | return  `true`       |
| 2    |  GRAVEL  | nein                                    | 0                       | return`false`        |
| 3    | EBIKE | nein                                    | 0                       | return  `false`      |
| 4    | RACE   | ja                                      | 1                       | return  `false`      |
| 5    | FIXIE | nein                                    | 4                       | return   `true`      |
| 6    | SINGLE_SPEED   | nein                                      | 5                       | return `false`       |
