package it.epicode;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import it.epicode.entities.utente.Utente;
import it.epicode.entities.utente.repository.UtenteRepository;
import it.epicode.entities.wallet.Wallet;
import it.epicode.entities.wallet.repository.WalletRepository;
import it.epicode.entities.wallet.services.WalletService;

@Component
@Order(2)
public class WalletRunner implements CommandLineRunner {

	@Autowired
	WalletService walletService;

	@Autowired
	WalletRepository walletRepo;

	@Autowired
	UtenteRepository utenteRepo;

	@Override
	public void run(String... args) throws Exception {

		List<Wallet> walletDB = walletRepo.findAll();

		List<Utente> utentiDB = utenteRepo.findAll();

		Faker faker = new Faker(new Locale("it"));

		if (walletDB.isEmpty()) {

			for (int i = 0; i < utentiDB.size(); i++) {
				Utente u = utentiDB.get(i);
				double saldoTotale = 50000;
				Wallet w = new Wallet(u, saldoTotale);
				walletRepo.save(w);
			}

		}

	}
}
