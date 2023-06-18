# CAPSTONE-PROJECT
# DigitFin Exchange 
Applicazione di scambio di criptovalute che consente agli utenti di acquistare, vendere e monitorare il valore delle criptovalute. L'applicazione offre una varietà di funzionalità avanzate e strumenti per consentire agli utenti di gestire le proprie operazioni in modo efficace.
# Tecnologie utilizzate

Front-end:

- React Bootstrap;
- React con Redux per la gestione dello stato;

Back-end:

Java 
- Spring Boot;
- Spring Data JPA;
- Spring Security Web;
- JSON Web Token (JWT);

Postgres come database per la persistenza dei dati

# Funzionalità
L'applicazione offre diverse funzionalità per gli utenti:

- Visualizzazione dei prezzi delle criptovalute:
L'applicazione utilizza le API di terze parti come CoinMarketCap o CoinGecko per ottenere i prezzi aggiornati delle criptovalute. Utilizza la libreria ApexCharts.js per visualizzare i grafici Candlestick nei diversi timeframe (1H, 4H, Daily, Weekly, Monthly), consentendo agli utenti di monitorare l'andamento dei prezzi.

- Registrazione degli utenti:
Gli utenti possono registrarsi per creare un account e accedere alle funzionalità complete dell'exchange. La registrazione consente inoltre di effettuare depositi e acquistare criptovalute. Spring Security Web e JSON Web Token (JWT) vengono utilizzati per garantire la sicurezza delle informazioni degli utenti durante il processo di registrazione e accesso.

- Acquisto e vendita di criptovalute:
Gli utenti possono effettuare transazioni di acquisto o vendita di criptovalute. Possono anche impostare ordini con stop loss o take profit per gestire le proprie posizioni.

- Visualizzazione del wallet:
L'applicazione consente agli utenti di visualizzare il saldo (in valuta fiat ) del totale delle loro posizioni e il dettaglio del loro portafoglio di criptovalute. Gli utenti possono vedere quali criptovalute possiedono. Per ogni criptovaluta detenuta nel portafoglio viene indicato il numero di pezzi posseduti, e il controvalore in valuta fiat .

- Sezione report:
Gli utenti possono accedere a un elenco dettagliato delle operazioni svolte, inclusi depositi, acquisti e vendite di criptovalute. Questa sezione fornisce agli utenti una panoramica completa delle loro attività e consente loro di generare report utili ai fini fiscali o per tenere traccia delle proprie posizioni nel tempo.

- Sezione di apprendimento:
L'applicazione offre una sezione dedicata all'apprendimento, che comprende articoli che partono dalle basi per i principianti fino ad articoli più avanzati per gli utenti esperti. Gli utenti possono accumulare punti rispondendo a quiz relativi agli articoli, che possono poi utilizzare per ottenere sconti sulle commissioni di transazione o accedere a ICO (Initial Coin Offering) in modo privilegiato.

# API CoinMarketCap

END POINT:

Restituisce la lista delle prime 100 criptovalute per market_cap

https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest?start=1&limit=100&convert=USD&CMC_PRO_API_KEY=API_KEY

DOCUMENTAZIONE:

https://coinmarketcap.com/api/documentation/v1/#operation/getV1CryptocurrencyListingsLatest
