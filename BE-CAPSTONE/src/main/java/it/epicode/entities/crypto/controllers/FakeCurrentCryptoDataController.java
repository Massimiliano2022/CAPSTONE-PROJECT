package it.epicode.entities.crypto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.entities.crypto.FakeCurrentCryptoData;
import it.epicode.entities.crypto.MonthlyCryptoData;
import it.epicode.entities.crypto.payloads.CriptovalutaUpdatePayload;
import it.epicode.entities.crypto.service.FakeCurrentCryptoDataService;
import it.epicode.entities.crypto.service.MonthlyCryptoDataService;

@RestController
@RequestMapping("/crypto")
public class FakeCurrentCryptoDataController {

	@Autowired
	private FakeCurrentCryptoDataService cryptoService;

	@Autowired
	private MonthlyCryptoDataService monthlyCryptoService;

	@GetMapping("")
	public ResponseEntity<List<FakeCurrentCryptoData>> getCripto() {
		List<FakeCurrentCryptoData> listaCripto = cryptoService.find();
		if (!listaCripto.isEmpty()) {
			return new ResponseEntity<>(listaCripto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/{simbolo}")
	public FakeCurrentCryptoData findBySimbolo(@PathVariable String simbolo) {
		return cryptoService.findBySimbolo(simbolo);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public FakeCurrentCryptoData updateBySibmolo(@PathVariable String simbolo,
			@RequestBody CriptovalutaUpdatePayload payload) {
		return cryptoService.findBySimboloAndUpadate(simbolo, payload.getNome(), payload.getPrezzo(),
				payload.getPercententuale_variazione_1h());
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	public void deleteBySibmolo(@PathVariable String simbolo) {
		cryptoService.findBySimboloAndDelete(simbolo);
	}

	@GetMapping("/monthly/{simbolo}")
	public ResponseEntity<List<MonthlyCryptoData>> getMonthlyCriptoData(@PathVariable String simbolo) {
		List<MonthlyCryptoData> listaCripto = monthlyCryptoService.findBySimbolo(simbolo);
		if (!listaCripto.isEmpty()) {
			return new ResponseEntity<>(listaCripto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

}
