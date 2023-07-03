package it.epicode.entities.wallet.controllers;

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
import it.epicode.entities.wallet.Wallet;
import it.epicode.entities.wallet.payloads.WalletUpdatePayload;
import it.epicode.entities.wallet.services.WalletService;

@RestController
@RequestMapping("/wallet")
public class WalletController {

	@Autowired
	WalletService walletService;

	@GetMapping("")
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public Page<Wallet> findAll(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "id") String order) {
		return walletService.findAll(page, order);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public Wallet findById(@PathVariable String id) {
		return walletService.findById(id);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public Wallet updateById(@PathVariable String id, @RequestBody WalletUpdatePayload payload) {
		return walletService.findByIdAndUpadate(id, payload);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	public void deleteById(@PathVariable String id) {
		walletService.findByIdAndDelete(id);
	}

	// IMPLEMENTARE ENDPOINT PER WALLET UTENTE CORRENTE

	@GetMapping("/me")

	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	public Wallet getWalletUtenteCorrente() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Utente utente = (Utente) authentication.getPrincipal();
		return walletService.findByUtente(utente.getId().toString());
	}

}
