package it.epicode.entities.crypto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import it.epicode.entities.crypto.CurrentCryptoData;
import it.epicode.entities.crypto.payloads.CriptovalutaUpdatePayload;
import it.epicode.entities.crypto.repository.CurrentCryptoDataRepository;
import it.epicode.exceptions.NotFoundException;

@Service
public class CurrentCryptoDataService {

	@Autowired
	CurrentCryptoDataRepository cryptoRepo;

	public CurrentCryptoData create(String simbolo, String nome, double prezzo, double percententuale_variazione_1h) {

		CurrentCryptoData c = new CurrentCryptoData(simbolo, nome, prezzo, percententuale_variazione_1h);

		return cryptoRepo.save(c);
	}

	public List<CurrentCryptoData> find() {
		return cryptoRepo.findAll();
	}

	public Page<CurrentCryptoData> findAll(int page, String ordinamento) {
		Pageable pagina = PageRequest.of(page, 10, Sort.by(ordinamento));
		return cryptoRepo.findAll(pagina);
	}

	public CurrentCryptoData findById(String id) {
		return cryptoRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("Criptovaluta : " + id + " non trovata!"));
	}

	public CurrentCryptoData findByIdAndUpadate(String id, CriptovalutaUpdatePayload body) {
		CurrentCryptoData c = findById(id);
		c.setNome(body.getNome());
		c.setPrezzo(body.getPrezzo());
		c.setTimestamp(body.getTimestamp());
		c.setPercententuale_variazione_1h(body.getPercententuale_variazione_1h());
		return cryptoRepo.save(c);
	}

	public void findByIdAndDelete(String id) {
		CurrentCryptoData c = findById(id);
		cryptoRepo.delete(c);
	}

}
