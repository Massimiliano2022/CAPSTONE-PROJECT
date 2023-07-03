package it.epicode.entities.utente.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import it.epicode.entities.utente.Role;
import it.epicode.entities.utente.Utente;
import it.epicode.entities.utente.payloads.UtentePayload;
import it.epicode.entities.utente.payloads.UtenteUpdatePayload;
import it.epicode.entities.utente.repository.UtenteRepository;
import it.epicode.exceptions.BadRequestException;
import it.epicode.exceptions.NotFoundException;

@Service
public class UtenteService {
	@Autowired
	private UtenteRepository utenteRepo;

	public Utente create(UtentePayload payload) {

		utenteRepo.findByEmail(payload.getEmail()).ifPresent(user -> {
			throw new BadRequestException("Email " + user.getEmail() + " già utilizzata!");
		});

		Utente u = new Utente(payload.getNome(), payload.getCognome(), payload.getEmail(), payload.getPassword());

		return utenteRepo.save(u);
	}

	public Utente findByEmail(String email) {
		return utenteRepo.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("Utente con " + email + " non trovato!"));
	}

	public Utente findById(String id) {
		return utenteRepo.findById(UUID.fromString(id))
				.orElseThrow(() -> new NotFoundException("Utente con id: " + id + " non trovato!"));
	}

	public void aggiungiAdmin(String email) {
		Utente found = this.findByEmail(email);
		found.setRuolo(Role.ADMIN);
		utenteRepo.save(found);
	}

	public List<Utente> find() {
		return utenteRepo.findAll();
	}

	public Page<Utente> findAll(int page, String ordinamento) {
		Pageable pagina = PageRequest.of(page, 10, Sort.by(ordinamento));
		return utenteRepo.findAll(pagina);
	}

	public Utente findByIdAndUpadate(String id, UtenteUpdatePayload body) {
		Utente u = findById(id);
		u.setEmail(body.getEmail());
		u.setNome(body.getNome());
		u.setCognome(body.getCognome());
		return utenteRepo.save(u);

	}

	public void findByIdAndDelete(String id) {
		Utente u = findById(id);

		utenteRepo.delete(u);

	}
}
