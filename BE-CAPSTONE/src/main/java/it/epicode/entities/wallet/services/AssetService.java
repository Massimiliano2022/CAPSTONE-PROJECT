package it.epicode.entities.wallet.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import it.epicode.entities.crypto.FakeCurrentCryptoData;
import it.epicode.entities.crypto.service.FakeCurrentCryptoDataService;
import it.epicode.entities.wallet.Asset;
import it.epicode.entities.wallet.Wallet;
import it.epicode.entities.wallet.repository.AssetRepository;
import it.epicode.exceptions.NotFoundException;

@Service
public class AssetService {

	@Autowired
	WalletService walletService;

	@Autowired
	FakeCurrentCryptoDataService cryptoService;

	@Autowired
	AssetRepository assetRepo;

	public Asset create(Wallet wallet, FakeCurrentCryptoData crypto, double quantita) {
		Asset a = new Asset(wallet, crypto, quantita);
		return assetRepo.save(a);
	}

	public List<Asset> find() {
		return assetRepo.findAll();
	}

	public Page<Asset> findAll(int page, String ordinamento) {
		Pageable pagina = PageRequest.of(page, 10, Sort.by(ordinamento));
		return assetRepo.findAll(pagina);
	}

	public Asset findById(String id) {
		return assetRepo.findById(UUID.fromString(id))
				.orElseThrow(() -> new NotFoundException("Asset con id: " + id + " non trovato!"));
	}

	public Asset findByCriptovaluta(String simbolo) {
		FakeCurrentCryptoData c = cryptoService.findBySimbolo(simbolo);
		return assetRepo.findByCrypto(c).orElseThrow(() -> new NotFoundException("Asset non trovato!"));
	}

	public Asset findByIdAndUpadate(String id, Wallet wallet, FakeCurrentCryptoData crypto, double quantita) {
		Asset a = findById(id);
		a.setWallet(wallet);
		a.setCrypto(crypto);
		a.setQuantita(quantita);
		return assetRepo.save(a);
	}

	public void findByIdAndDelete(String id) {
		Asset a = findById(id);
		assetRepo.delete(a);
	}
}
