# Sito Gielle S.r.l.

Nuovo sito di Gielle S.r.l., officina meccanica di precisione a Scandicci (FI).
Sito statico: HTML, CSS e JavaScript senza dipendenze né build.

## Struttura

```
index.html          Home (resta nella cartella principale: è la prima pagina che si apre)
pagine/             Le altre pagine del sito
  storia.html         Storia
  qualita.html        Qualità (certificazioni, laboratorio, CND, metrologia)
  ambiti.html         Ambiti (settori, materiali, servizi)
  tecnologie.html     Tecnologie (parco macchine reparto per reparto)
  contatti.html       Contatti, modulo richieste e "Lavora con noi" con invio CV
  privacy.html        Privacy Policy (testo da inserire)
css/style.css       Stili (colori, font, layout responsive)
js/main.js          Header allo scroll, menu mobile, animazioni di comparsa
assets/img/         Foto (per ora segnaposto da sostituire con foto reali)
assets/logo/        Logo: simbolo esagonale (positivo e negativo) e logo istituzionale
```

## Vedere l'anteprima in Visual Studio Code

1. Installa l'estensione **Live Server** (di Ritwick Dey).
2. Apri `index.html`, clic destro → **Open with Live Server**.
3. Il sito si apre nel browser e si aggiorna da solo a ogni salvataggio.
   Per la versione mobile: nel browser premi F12 e attiva la vista dispositivo.

## Modificare header e footer

Header e footer sono ripetuti uguali in ogni pagina: se cambi una voce del menu o un recapito, cambiala in tutti i file `.html` (in VS Code: Ctrl+Shift+H, "Sostituisci nei file").

## Moduli (contatti e candidature)

I moduli controllano i campi obbligatori ma non inviano ancora nulla: un sito statico ha bisogno di un servizio esterno per ricevere i messaggi e gli allegati (ad esempio Formspree, Netlify Forms o uno script PHP sul server dell'hosting). Va deciso con chi pubblicherà il sito.

## Colori e font

- Nero logo `#231F20`, verde logo `#A6D820`, grigi acciaio (variabili in cima a `css/style.css`).
- Font: Barlow Condensed (titoli), Barlow (testi), IBM Plex Mono (etichette), da Google Fonts.

## Da completare

- Sostituire le foto segnaposto in `assets/img/` con foto reali (stessi nomi file).
- Inserire i loghi reali delle certificazioni nella sezione Qualità.
- Confermare con l'azienda la versione attuale della certificazione ISO 9001 e la portata della macchina di trazione (il vecchio sito indica sia 20 t sia 40 t).
- Versione inglese (il pulsante EN è già nel menu).
- Testi della pagina Ambiti: sono una bozza da far validare all'azienda.
- Collegare i moduli a un servizio di invio (vedi sopra) e inserire l'informativa privacy.
