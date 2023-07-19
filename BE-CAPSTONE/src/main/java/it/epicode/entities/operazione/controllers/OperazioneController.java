package it.epicode.entities.operazione.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.entities.operazione.Operazione;
import it.epicode.entities.operazione.payloads.OperazionePayload;
import it.epicode.entities.operazione.services.OperazioneService;
import it.epicode.entities.utente.Utente;
import it.epicode.entities.wallet.Wallet;
import it.epicode.entities.wallet.services.WalletService;

@RestController
@RequestMapping("/operazioni")
public class OperazioneController {

	@Autowired
	OperazioneService operazioneService;

	@Autowired
	WalletService walletService;

	@GetMapping("")
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public Page<Operazione> findAll(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "id") String order) {
		return operazioneService.findAll(page, order);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public Operazione findById(@PathVariable String id) {
		return operazioneService.findById(id);
	}

	@PostMapping("")
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	public Operazione createOperazione(@RequestBody @Validated OperazionePayload payload) {
		return operazioneService.create(payload);
	}

	/*
	 * @PutMapping("/{id}") public Operazione updateById(@PathVariable String
	 * id, @RequestBody OperazionePayload payload) { return
	 * operazioneService.findByIdAndUpadate(id, payload); }
	 */

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	public void deleteById(@PathVariable String id) {
		operazioneService.findByIdAndDelete(id);
	}

	@GetMapping("/me")
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	public Page<Operazione> findOperazioniByCustomFilters(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "dataOperazione") String order,
			@RequestParam(required = false) String tipoOperazione, @RequestParam(required = false) String simboloCrypto,
			@RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Utente utente = (Utente) authentication.getPrincipal();
		Wallet w = walletService.findByUtente(utente.getId().toString());

		return operazioneService.findOperazioniByCustomFilters(page, order, w.getId().toString(), tipoOperazione,
				simboloCrypto, startDate, endDate);
	}
}
