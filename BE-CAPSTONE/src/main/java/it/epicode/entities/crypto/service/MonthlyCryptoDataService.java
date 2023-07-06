package it.epicode.entities.crypto.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import it.epicode.entities.crypto.MonthlyCryptoData;
import it.epicode.entities.crypto.repository.MonthlyCryptoDataRepository;
import it.epicode.exceptions.NotFoundException;

@Service
public class MonthlyCryptoDataService {

	@Autowired
	MonthlyCryptoDataRepository monthlyCryptoRepo;

	public MonthlyCryptoData create(String simbolo, String nome, double chiusuraPrezzo, double variazionePrezzo,
			LocalDate data) {

		MonthlyCryptoData c = new MonthlyCryptoData(simbolo, nome, chiusuraPrezzo, variazionePrezzo, data);

		return monthlyCryptoRepo.save(c);
	}

	public List<MonthlyCryptoData> find() {
		return monthlyCryptoRepo.findAll();
	}

	public Page<MonthlyCryptoData> findAll(int page, String ordinamento) {
		Pageable pagina = PageRequest.of(page, 10, Sort.by(ordinamento));
		return monthlyCryptoRepo.findAll(pagina);
	}

	public MonthlyCryptoData findById(String id) {
		return monthlyCryptoRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("Criptovaluta : " + id + " non trovata!"));
	}

	// LISTA DATI MENSILI
	public List<MonthlyCryptoData> findBySimbolo(String simbolo) {
		return monthlyCryptoRepo.findBySimbolo(simbolo);
	}

	public MonthlyCryptoData findByIdAndUpadate(String id, String nome, double chiusuraPrezzo, double variazionePrezzo,
			LocalDate data) {
		MonthlyCryptoData c = findById(id);
		c.setNome(nome);
		c.setChiusuraPrezzo(chiusuraPrezzo);
		c.setVariazionePrezzo(variazionePrezzo);
		c.setData(data);
		return monthlyCryptoRepo.save(c);
	}

	public void findByIdAndDelete(String id) {
		MonthlyCryptoData c = findById(id);
		monthlyCryptoRepo.delete(c);
	}

}
