package it.epicode;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import it.epicode.entities.crypto.FakeCurrentCryptoData;
import it.epicode.entities.crypto.MonthlyCryptoData;
import it.epicode.entities.crypto.repository.CurrentCryptoDataRepository;
import it.epicode.entities.crypto.repository.FakeCurrentCryptoDataRepository;
import it.epicode.entities.crypto.repository.MonthlyCryptoDataRepository;
import it.epicode.entities.crypto.service.FakeCurrentCryptoDataService;
import it.epicode.entities.crypto.service.MonthlyCryptoDataService;
import it.epicode.thirdPartyAPI.service.CoinMarketCapAPIService;

@Component
@Order(0)
public class CryptoRunner implements CommandLineRunner {

	@Autowired
	CoinMarketCapAPIService cmcService;

	@Autowired
	CurrentCryptoDataRepository cryptoRepo;

	@Autowired
	MonthlyCryptoDataRepository monthlyCryptoRepo;

	@Autowired
	MonthlyCryptoDataService monthlyCryptoService;

	@Autowired
	FakeCurrentCryptoDataService fakeCurrentCryptoDataService;

	@Autowired
	FakeCurrentCryptoDataRepository fakeCurrentCryptoDataRepo;

	@Override
	public void run(String... args) throws Exception {
		// Se firstRun = true eseguo la richiesta get all'API di CoinMarketCap
		// e se db già popolato aggiorno i dati con quelli più recenti
		// ottenuti come risposta dalla get, in modo che ad ogni avvio
		// i prezzi siano congrui alla realta
		boolean firstRun = true;

		List<MonthlyCryptoData> cryptoMonthlyDB = monthlyCryptoRepo.findAll();

		if (firstRun) {
			cmcService.getRequest();
			firstRun = false;
		}
		if (cryptoMonthlyDB.isEmpty()) {
			// BTC
			monthlyCryptoService.create("BTC", "Bitcoin", 16537.4, -3.65, LocalDate.of(2022, 12, 31));
			monthlyCryptoService.create("BTC", "Bitcoin", 23125.1, 39.83, LocalDate.of(2023, 1, 31));
			monthlyCryptoService.create("BTC", "Bitcoin", 23130.5, 0.02, LocalDate.of(2023, 2, 28));
			monthlyCryptoService.create("BTC", "Bitcoin", 28473.7, 23.10, LocalDate.of(2023, 3, 31));
			monthlyCryptoService.create("BTC", "Bitcoin", 29252.1, 2.73, LocalDate.of(2023, 4, 30));
			monthlyCryptoService.create("BTC", "Bitcoin", 27216.1, -6.96, LocalDate.of(2023, 5, 31));
			monthlyCryptoService.create("BTC", "Bitcoin", 30472.9, 11.97, LocalDate.of(2023, 6, 30));

			// ETH
			monthlyCryptoService.create("ETH", "Ethereum", 1195.67, -7.63, LocalDate.of(2022, 12, 31));
			monthlyCryptoService.create("ETH", "Ethereum", 1585.27, 32.58, LocalDate.of(2023, 1, 31));
			monthlyCryptoService.create("ETH", "Ethereum", 1604.69, 1.23, LocalDate.of(2023, 2, 28));
			monthlyCryptoService.create("ETH", "Ethereum", 1821.62, 13.52, LocalDate.of(2023, 3, 31));
			monthlyCryptoService.create("ETH", "Ethereum", 1868.88, 2.59, LocalDate.of(2023, 4, 30));
			monthlyCryptoService.create("ETH", "Ethereum", 1873.63, 0.25, LocalDate.of(2023, 5, 31));
			monthlyCryptoService.create("ETH", "Ethereum", 1933.80, 3.21, LocalDate.of(2023, 6, 30));

			// ADA
			monthlyCryptoService.create("ADA", "Cardano", 0.2456, -22.93, LocalDate.of(2022, 12, 31));
			monthlyCryptoService.create("ADA", "Cardano", 0.3903, 58.89, LocalDate.of(2023, 1, 31));
			monthlyCryptoService.create("ADA", "Cardano", 0.3518, -9.86, LocalDate.of(2023, 2, 28));
			monthlyCryptoService.create("ADA", "Cardano", 0.3989, 13.39, LocalDate.of(2023, 3, 31));
			monthlyCryptoService.create("ADA", "Cardano", 0.3955, -0.84, LocalDate.of(2023, 4, 30));
			monthlyCryptoService.create("ADA", "Cardano", 0.3744, -5.35, LocalDate.of(2023, 5, 31));
			monthlyCryptoService.create("ADA", "Cardano", 0.2867, -23.42, LocalDate.of(2023, 6, 30));

			// DOT
			monthlyCryptoService.create("DOT", "Polkadot", 4.300, -21.39, LocalDate.of(2022, 12, 31));
			monthlyCryptoService.create("DOT", "Polkadot", 6.262, 45.62, LocalDate.of(2023, 1, 31));
			monthlyCryptoService.create("DOT", "Polkadot", 6.345, 1.33, LocalDate.of(2023, 2, 28));
			monthlyCryptoService.create("DOT", "Polkadot", 6.343, -0.03, LocalDate.of(2023, 3, 31));
			monthlyCryptoService.create("DOT", "Polkadot", 5.874, -7.39, LocalDate.of(2023, 4, 30));
			monthlyCryptoService.create("DOT", "Polkadot", 5.316, -9.50, LocalDate.of(2023, 5, 31));
			monthlyCryptoService.create("DOT", "Polkadot", 5.182, -2.52, LocalDate.of(2023, 6, 30));

			// MATIC
			monthlyCryptoService.create("MATIC", "Matic", 0.758, -18.80, LocalDate.of(2022, 12, 31));
			monthlyCryptoService.create("MATIC", "Matic", 1.109, 46.33, LocalDate.of(2023, 1, 31));
			monthlyCryptoService.create("MATIC", "Matic", 1.195, 7.71, LocalDate.of(2023, 2, 28));
			monthlyCryptoService.create("MATIC", "Matic", 1.118, -6.42, LocalDate.of(2023, 3, 31));
			monthlyCryptoService.create("MATIC", "Matic", 0.980, -12.32, LocalDate.of(2023, 4, 30));
			monthlyCryptoService.create("MATIC", "Matic", 0.892, -8.97, LocalDate.of(2023, 5, 31));
			monthlyCryptoService.create("MATIC", "Matic", 0.662, -25.88, LocalDate.of(2023, 6, 30));

			// XRP
			monthlyCryptoService.create("XRP", "Ripple", 0.33872, -16.94, LocalDate.of(2022, 12, 31));
			monthlyCryptoService.create("XRP", "Ripple", 0.40572, 19.78, LocalDate.of(2023, 1, 31));
			monthlyCryptoService.create("XRP", "Ripple", 0.37620, -7.27, LocalDate.of(2023, 2, 28));
			monthlyCryptoService.create("XRP", "Ripple", 0.53799, 43.01, LocalDate.of(2023, 3, 31));
			monthlyCryptoService.create("XRP", "Ripple", 0.47052, -12.54, LocalDate.of(2023, 4, 30));
			monthlyCryptoService.create("XRP", "Ripple", 0.51678, 9.83, LocalDate.of(2023, 5, 31));
			monthlyCryptoService.create("XRP", "Ripple", 0.47293, -8.49, LocalDate.of(2023, 6, 30));

			// DOGE
			monthlyCryptoService.create("DOGE", "Dogecoin", 0.070144, -34.31, LocalDate.of(2022, 12, 31));
			monthlyCryptoService.create("DOGE", "Dogecoin", 0.096096, 37.00, LocalDate.of(2023, 1, 31));
			monthlyCryptoService.create("DOGE", "Dogecoin", 0.080792, -15.93, LocalDate.of(2023, 2, 28));
			monthlyCryptoService.create("DOGE", "Dogecoin", 0.076984, -4.71, LocalDate.of(2023, 3, 31));
			monthlyCryptoService.create("DOGE", "Dogecoin", 0.079462, 3.22, LocalDate.of(2023, 4, 30));
			monthlyCryptoService.create("DOGE", "Dogecoin", 0.071661, -9.82, LocalDate.of(2023, 5, 31));
			monthlyCryptoService.create("DOGE", "Dogecoin", 0.066535, -7.15, LocalDate.of(2023, 6, 30));

			// SAND
			monthlyCryptoService.create("SAND", "The Sandbox", 0.3826, -35.26, LocalDate.of(2022, 12, 31));
			monthlyCryptoService.create("SAND", "The Sandbox", 0.7301, 90.83, LocalDate.of(2023, 1, 31));
			monthlyCryptoService.create("SAND", "The Sandbox", 0.6837, -6.36, LocalDate.of(2023, 2, 28));
			monthlyCryptoService.create("SAND", "The Sandbox", 0.6274, -8.23, LocalDate.of(2023, 3, 31));
			monthlyCryptoService.create("SAND", "The Sandbox", 0.5769, -8.05, LocalDate.of(2023, 4, 30));
			monthlyCryptoService.create("SAND", "The Sandbox", 0.5321, -7.76, LocalDate.of(2023, 5, 31));
			monthlyCryptoService.create("SAND", "The Sandbox", 0.4190, -21.26, LocalDate.of(2023, 6, 30));
		}

		Thread aggiornaPrezziThread = new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(Duration.ofMinutes(1).toMillis());
					creaPrezziFake();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		aggiornaPrezziThread.start();
	}

	private void creaPrezziFake() {
		Random random = new Random();

		List<FakeCurrentCryptoData> fakeCurrentCryptoDataList = fakeCurrentCryptoDataRepo.findAll();

		fakeCurrentCryptoDataList.sort(Comparator.comparingInt(FakeCurrentCryptoData::getId)); // Ordina per ID

		for (FakeCurrentCryptoData fakeCurrentCryptoData : fakeCurrentCryptoDataList) {
			String simbolo = fakeCurrentCryptoData.getSimbolo();

			List<MonthlyCryptoData> monthlyCryptoDataList = monthlyCryptoRepo.findBySimbolo(simbolo);

			MonthlyCryptoData randomMonthlyData = monthlyCryptoDataList
					.get(random.nextInt(monthlyCryptoDataList.size()));

			double segnoRandom = random.nextBoolean() ? 1.0 : -1.0; // Genera un segno randomico

			double variazionePercentualeMensile = randomMonthlyData.getVariazionePrezzo();

			double minPercentualeRandom = 0.0;
			double maxPecentualeRandom = variazionePercentualeMensile;

			if (variazionePercentualeMensile > 20.0) {
				maxPecentualeRandom = variazionePercentualeMensile / 6.0;

				variazionePercentualeMensile = maxPecentualeRandom;

			}

			double percentualeRandom = minPercentualeRandom
					+ random.nextDouble() * (variazionePercentualeMensile - minPercentualeRandom);

			percentualeRandom *= segnoRandom;

			double cambioPrezzoFittizio = fakeCurrentCryptoData.getPrezzo() * (percentualeRandom / 100.0);

			double fakeCurrentPrice = fakeCurrentCryptoData.getPrezzo() + cambioPrezzoFittizio;

			percentualeRandom = Math.round(percentualeRandom * 100000) / 100000.0;
			fakeCurrentPrice = Math.round(fakeCurrentPrice * 100000) / 100000.0;

			String percentualeFormatted = (percentualeRandom >= 0) ? "+" + percentualeRandom
					: String.valueOf(percentualeRandom);

			fakeCurrentCryptoData.setId(fakeCurrentCryptoData.getId());
			fakeCurrentCryptoData.setSimbolo(fakeCurrentCryptoData.getSimbolo());
			fakeCurrentCryptoData.setNome(fakeCurrentCryptoData.getNome());
			fakeCurrentCryptoData.setPrezzo(fakeCurrentPrice);
			fakeCurrentCryptoData.setTimestamp(LocalDateTime.now(ZoneId.of("Europe/Rome")));
			fakeCurrentCryptoData.setPercententuale_variazione_1h(percentualeFormatted);

			fakeCurrentCryptoDataRepo.save(fakeCurrentCryptoData);

		}
	}
}
