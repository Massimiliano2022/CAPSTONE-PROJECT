package it.epicode.entities.utente.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.entities.utente.Utente;
import it.epicode.entities.utente.payloads.UtenteUpdatePayload;
import it.epicode.entities.utente.services.UtenteService;

@RestController
@RequestMapping("/utenti")
public class UtenteController {
	@Autowired
	UtenteService utenteService;

	@GetMapping("")
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public Page<Utente> findAll(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "id") String order) {
		return utenteService.findAll(page, order);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	public Utente findById(@PathVariable String id) {
		return utenteService.findById(id);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public Utente updateById(@PathVariable String id, @RequestBody UtenteUpdatePayload payload) {
		return utenteService.findByIdAndUpadate(id, payload);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	public void deleteById(@PathVariable String id) {
		utenteService.findByIdAndDelete(id);
	}

	@GetMapping("/me")
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	public Utente getUtenteCorrente() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Utente utente = (Utente) authentication.getPrincipal();
		return utenteService.findById(utente.getId().toString());
	}

}
