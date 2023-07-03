package it.epicode.entities.wallet.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import it.epicode.entities.utente.Utente;
import it.epicode.entities.utente.services.UtenteService;
import it.epicode.entities.wallet.Wallet;
import it.epicode.entities.wallet.payloads.WalletUpdatePayload;
import it.epicode.entities.wallet.repository.WalletRepository;
import it.epicode.exceptions.NotFoundException;

@Service
public class WalletService {

	@Autowired
	WalletRepository walletRepo;

	@Autowired
	UtenteService utenteService;

	public Wallet create(Utente utente, double saldoDisponibile) {

		Wallet w = new Wallet(utente, saldoDisponibile);

		return walletRepo.save(w);
	}

	public List<Wallet> find() {
		return walletRepo.findAll();
	}

	public Page<Wallet> findAll(int page, String ordinamento) {
		Pageable pagina = PageRequest.of(page, 10, Sort.by(ordinamento));
		return walletRepo.findAll(pagina);
	}

	public Wallet findById(String id) {
		return walletRepo.findById(UUID.fromString(id))
				.orElseThrow(() -> new NotFoundException("Wallet con id: " + id + " non trovato!"));
	}

	public Wallet findByIdAndUpadate(String id, double nuovoSaldo) {
		Wallet w = findById(id);
		w.setSaldoDisponibile(nuovoSaldo);
		return walletRepo.save(w);
	}

	public Wallet findByIdAndUpadate(String id, WalletUpdatePayload body) {
		Wallet w = findById(id);
		w.setSaldoDisponibile(body.getSaldoDisponibile());
		return walletRepo.save(w);
	}

	public void findByIdAndDelete(String id) {
		Wallet w = findById(id);
		walletRepo.delete(w);
	}

	public Wallet findByUtente(String idUtente) {

		Utente u = utenteService.findById(idUtente);

		return walletRepo.findByUtente(u)
				.orElseThrow(() -> new NotFoundException("Wallet non trovato per Utente con id: " + u.getId()));
	}

}
