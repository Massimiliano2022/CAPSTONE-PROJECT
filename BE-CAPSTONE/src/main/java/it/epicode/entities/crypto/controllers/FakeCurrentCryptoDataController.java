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
import it.epicode.entities.crypto.payloads.CriptovalutaUpdatePayload;
import it.epicode.entities.crypto.service.FakeCurrentCryptoDataService;

@RestController
@RequestMapping("/crypto")
public class FakeCurrentCryptoDataController {

	@Autowired
	private FakeCurrentCryptoDataService cryptoService;

	@GetMapping("")
	public ResponseEntity<List<FakeCurrentCryptoData>> getCripto() {
		List<FakeCurrentCryptoData> listaCripto = cryptoService.find();
		if (!listaCripto.isEmpty()) {
			return new ResponseEntity<>(listaCripto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/{id}")
	public FakeCurrentCryptoData findById(@PathVariable String id) {
		return cryptoService.findById(id);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public FakeCurrentCryptoData updateById(@PathVariable String id, @RequestBody CriptovalutaUpdatePayload payload) {
		return cryptoService.findByIdAndUpadate(id, payload);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	public void deleteById(@PathVariable String id) {
		cryptoService.findByIdAndDelete(id);
	}

}
