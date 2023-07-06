package it.epicode.entities.crypto.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import it.epicode.entities.crypto.FakeCurrentCryptoData;
import it.epicode.entities.crypto.payloads.CriptovalutaUpdatePayload;
import it.epicode.entities.crypto.repository.FakeCurrentCryptoDataRepository;
import it.epicode.exceptions.NotFoundException;

@Service
public class FakeCurrentCryptoDataService {

	@Autowired
	FakeCurrentCryptoDataRepository cryptoRepo;

	public FakeCurrentCryptoData create(String simbolo, String nome, double prezzo,
			String percententuale_variazione_1h) {

		FakeCurrentCryptoData c = new FakeCurrentCryptoData(simbolo, nome, prezzo, percententuale_variazione_1h);

		return cryptoRepo.save(c);
	}

	public List<FakeCurrentCryptoData> find() {
		return cryptoRepo.findAll();
	}

	public Page<FakeCurrentCryptoData> findAll(int page, String ordinamento) {
		Pageable pagina = PageRequest.of(page, 10, Sort.by(ordinamento));
		return cryptoRepo.findAll(pagina);
	}

	public FakeCurrentCryptoData findById(int id) {
		return cryptoRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("Criptovaluta : " + id + " non trovata!"));
	}

	public FakeCurrentCryptoData findBySimbolo(String simbolo) {
		return cryptoRepo.findBySimbolo(simbolo)
				.orElseThrow(() -> new NotFoundException("Criptovaluta : " + simbolo + " non trovata!"));
	}

	public FakeCurrentCryptoData findByIdAndUpadate(int id, CriptovalutaUpdatePayload body) {
		FakeCurrentCryptoData c = findById(id);
		c.setNome(body.getNome());
		c.setPrezzo(body.getPrezzo());
		c.setTimestamp(body.getTimestamp());
		c.setPercententuale_variazione_1h(body.getPercententuale_variazione_1h());
		return cryptoRepo.save(c);
	}

	public FakeCurrentCryptoData findBySimboloAndUpadate(String simbolo, String nome, double prezzo,
			String percententuale_variazione_1h) {
		FakeCurrentCryptoData c = findBySimbolo(simbolo);
		c.setNome(nome);
		c.setPrezzo(prezzo);
		c.setTimestamp(LocalDateTime.now(ZoneId.of("Europe/Rome")));
		c.setPercententuale_variazione_1h(percententuale_variazione_1h);
		return cryptoRepo.save(c);
	}

	public void findByIdAndDelete(int id) {
		FakeCurrentCryptoData c = findById(id);
		cryptoRepo.delete(c);
	}

	public void findBySimboloAndDelete(String simbolo) {
		FakeCurrentCryptoData c = findBySimbolo(simbolo);
		cryptoRepo.delete(c);
	}

}
