package it.epicode;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import it.epicode.entities.utente.Utente;
import it.epicode.entities.utente.payloads.UtentePayload;
import it.epicode.entities.utente.repository.UtenteRepository;
import it.epicode.entities.utente.services.UtenteService;

@Component
@Order(1)
public class UtenteRunner implements CommandLineRunner {

	@Autowired
	UtenteService utenteService;

	@Autowired
	UtenteRepository utenteRepo;

	@Autowired
	private PasswordEncoder bcrypt;

	@Override
	public void run(String... args) throws Exception {

		List<Utente> utentiDB = utenteRepo.findAll();
		Faker faker = new Faker(new Locale("it"));
		if (utentiDB.isEmpty()) {
			for (int i = 0; i < 5; i++) {
				String email = faker.internet().emailAddress();
				String password = "1234";
				String nome = faker.name().firstName();
				String cognome = faker.name().lastName();
				UtentePayload payload = new UtentePayload(nome, cognome, email, password);
				payload.setPassword(bcrypt.encode(payload.getPassword()));
				utenteService.create(payload);
				utenteService.aggiungiAdmin(email);
			}

			for (int j = 0; j < 5; j++) {
				String email = faker.internet().emailAddress();
				String password = "5678";
				String nome = faker.name().firstName();
				String cognome = faker.name().lastName();
				UtentePayload payload = new UtentePayload(nome, cognome, email, password);
				payload.setPassword(bcrypt.encode(payload.getPassword()));
				utenteService.create(payload);
			}

		}
	}

}
