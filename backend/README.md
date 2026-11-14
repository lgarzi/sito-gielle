# Backend del sito Gielle

Piccola applicazione **Spring Boot** che riceve i moduli del sito e li salva in un database **MySQL**:

- **Contatti**: le richieste dal modulo della pagina Contatti, con l'eventuale disegno tecnico allegato.
- **Lavora con noi**: le candidature, con il curriculum allegato.

I file allegati (disegni e CV) vengono salvati nella cartella `allegati/`; nel database resta solo il loro percorso.

## Cosa serve

1. **Java 17** o più recente (per esempio *Eclipse Temurin* da adoptium.net).
2. **Visual Studio Code** con le estensioni **Extension Pack for Java** e **Spring Boot Extension Pack**.
3. **MySQL 8** (MySQL Community Server; MySQL Workbench è comodo per guardare i dati). Non serve per la prima prova: vedi sotto.

Maven non va installato a parte: le estensioni Java di VS Code lo usano da sole.

## Prima prova, senza MySQL

1. In VS Code apri la cartella `backend` (File, Apri cartella).
2. Aspetta che in basso finisca il caricamento del progetto Java.
3. Apri `src/main/java/com/gielle/sito/SitoGielleApplication.java`.
4. Nel pannello **Spring Boot Dashboard** (o con il tasto destro, *Run*) avvia l'app con il profilo `dev`. In alternativa, da terminale nella cartella `backend`:

   ```
   mvn spring-boot:run -Dspring-boot.run.profiles=dev
   ```

Con il profilo `dev` i dati vanno in un database di prova (H2) dentro la cartella `dati/`, che si crea da solo. Puoi vederlo su http://localhost:8080/h2-console (JDBC URL `jdbc:h2:file:./dati/gielle-dev`, utente `sa`, password vuota).

## Con MySQL

1. Apri `db/schema.sql` e cambia la password `cambia-questa-password` con una tua.
2. Eseguilo una volta come utente root, da MySQL Workbench oppure con:

   ```
   mysql -u root -p < db/schema.sql
   ```

   Crea il database `gielle`, l'utente `gielle` e le due tabelle `richieste_contatto` e `candidature`.
3. Metti la stessa password in `src/main/resources/application.properties` (`spring.datasource.password`).
4. Avvia l'app senza profilo: `mvn spring-boot:run`.

## Indirizzi dell'API

Entrambi accettano `multipart/form-data`, cioè lo stesso formato di un normale modulo HTML con allegati.

| Indirizzo | Campi | Allegato |
|---|---|---|
| `POST /api/contatti` | `nome`*, `email`*, `messaggio`*, `azienda`, `telefono`, `oggetto`, `privacy`* | `disegno` (pdf, dxf, dwg, step, stp, igs, iges) |
| `POST /api/candidature` | `nome`*, `cognome`*, `email`*, `telefono`*, `posizione`, `messaggio`, `privacy`* | `curriculum`* (pdf, doc, docx) |

I campi con * sono obbligatori e `privacy` deve valere `true`. Gli allegati possono pesare al massimo 10 MB.

Le risposte sono queste:

- `201` con `{"id": ...}` quando la richiesta è stata salvata.
- `400` con l'elenco dei campi da correggere.
- `413` se il file è troppo grande.

## Test

```
mvn test
```

Prova i due moduli su un database temporaneo, con un caso valido e uno sbagliato per ciascuno.

## Configurazione utile

In `src/main/resources/application.properties`:

- `gielle.allegati.cartella`: dove salvare disegni e CV.
- `gielle.sito.origini`: da quali indirizzi il sito può inviare i moduli. Ci sono già Live Server di VS Code (porta 5500) e www.giellesrl.com.
