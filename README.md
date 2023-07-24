# CAPSTONE-PROJECT
# DigitFin Exchange 
Applicazione di scambio di criptovalute che consente agli utenti di acquistare, vendere e monitorare il valore delle criptovalute. L'applicazione offre una varietà di funzionalità avanzate e strumenti per consentire agli utenti di gestire le proprie operazioni in modo efficace.
# Tecnologie utilizzate

Back-end:

Java 
- lombok : libreria che fornisce un set di annotazioni per eliminare il boilerplate code (codice ripetitivo) all'interno delle classi Java. Con Lombok, possiamo ridurre notevolmente la quantità di codice necessario per definire getter, setter, costruttori e altro, permettendoci di concentrarci maggiormente sulla logica dell'applicazione.
- Spring Starter Data JPA : libreria Spring Boot per semplificare l'interazione con il database utilizzando la tecnologia Java Persistence API (JPA).
  Permette di mappare le entità dell'applicazione (classi Java) alle  tabelle del database, consentendo così di interagire con il database utilizzando oggetti Java 
  anziché scrivere query SQL direttamente;
- PostgreSQL : grazie a questo driver, possiamo stabilire una connessione sicura e affidabile tra l'applicazione e il database, permettendo di archiviare e recuperare i dati in modo efficiente;
- Spring Starter Web : libreria Spring Boot per la creazione di un web service in grado di gestire le richieste HTTP (GET/POST/PUT/DELETE);
- Spring Starter Security : libreria Spring Boot per la gestione della sicurezza della nostra applicazione. Offre una configurazione di sicurezza di base che può essere personalizzata per le esigenze specifiche della nostra applicazione. Possiamo definire regole di accesso, autenticazione e autorizzazione per proteggere i dati e garantire un ambiente sicuro per gli utenti.;
- JSON Web Token (JWT) : In combinazione con Spring Starter Security, le librerie relative ai JSON Web Token (JWT) ci permettono di gestire in modo efficace la sicurezza dell'applicazione. Il meccanismo JWT offre una codifica sicura per le informazioni sensibili degli utenti, come le password, garantendo al contempo la possibilità di verificare l'autenticità degli utenti per gli accessi futuri.
- Spring Starter Validation : libreria per la validazione dei dati gestiti dall'applicazione ,grazie a una serie di annotazioni, possiamo verificare la correttezza dei dati di input e garantire che siano conformi ai nostri requisiti, migliorando l'affidabilità e la stabilità dell'applicazione.

Postgres come database per la persistenza dei dati

[DigitFin Exchange Frontend Repository](https://github.com/Massimiliano2022/CAPSTONE-PROJECT-FRONTEND)

# Funzionalità
L'applicazione offre diverse funzionalità per gli utenti:

- Visualizzazione dei prezzi delle criptovalute:
L'applicazione utilizza le API di terze parti (CoinMarketCap) per ottenere i prezzi aggiornati delle criptovalute. Attraverso la libreria react-chartjs-2 mostra il grafico lineare nel timeframe mensile, consentendo agli utenti di monitorare l'andamento dei prezzi. Nella pagina dei mercati (/mercati), si possono effettuare ricerche per nome o simbolo della criptovaluta per trovare rapidamente le informazioni di cui si ha bisogno. Questa funzionalità è disponibile sia per gli utenti registrati che per quelli non registrati.

- Registrazione degli utenti:
Gli utenti possono registrarsi e accedere alle funzionalità complete dell'exchange. Durante il processo di registrazione, vengono richieste informazioni come nome,cognome, indirizzo email e password. La registrazione consente di effettuare delle operazioni. Spring Security Web e JSON Web Token (JWT) vengono utilizzati per garantire la sicurezza delle informazioni degli utenti durante il processo di registrazione e accesso.

- Acquisto e vendita di criptovalute:
Una volta registrati e autenticati, gli utenti hanno la possibilità di effettuare transazioni di acquisto o vendita di criptovalute. L'applicazione consente loro di selezionare la criptovaluta desiderata, specificare la quantità e scegliere l'azione da compiere (acquisto o vendita).

- Visualizzazione del wallet:
L'applicazione offre agli utenti la possibilità di visualizzare il saldo totale delle loro posizioni in valuta fiat e il dettaglio del portafoglio crypto. Nel wallet, gli utenti possono vedere tutte le criptovalute che possiedono, insieme alla quantità di ciascuna. Per facilitare la comprensione, l'applicazione mostra anche il controvalore in valuta fiat per ciascuna criptovaluta posseduta. Inoltre, attraverso la libreria react-chartjs-2, viene mostrato un grafico in cui è possibile monitorare le percentuali di ciascun asset detenuto nel portafoglio dell'utente.

- Visualizzazione lista operazioni:
Gli utenti possono accedere a un elenco dettagliato delle operazioni svolte (acquisti e vendite). Questa sezione fornisce agli utenti una panoramica completa delle loro attività e consente loro di generare report utili ai fini fiscali o per tenere traccia delle proprie posizioni nel tempo. E' possibile filtrare le operazioni per:
  - range di date;
  - tipo operazione (BUY/SELL);
  - crypto.


