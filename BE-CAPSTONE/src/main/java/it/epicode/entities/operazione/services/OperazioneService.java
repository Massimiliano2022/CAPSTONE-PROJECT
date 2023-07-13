package it.epicode.entities.operazione.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
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
import it.epicode.entities.operazione.Operazione;
import it.epicode.entities.operazione.TipoOperazione;
import it.epicode.entities.operazione.payloads.OperazionePayload;
import it.epicode.entities.operazione.repository.OperazioneRepository;
import it.epicode.entities.utente.services.UtenteService;
import it.epicode.entities.wallet.Asset;
import it.epicode.entities.wallet.Wallet;
import it.epicode.entities.wallet.services.AssetService;
import it.epicode.entities.wallet.services.WalletService;
import it.epicode.exceptions.BadRequestException;
import it.epicode.exceptions.NotFoundException;

@Service
public class OperazioneService {

	@Autowired
	OperazioneRepository operazioneRepo;

	@Autowired
	WalletService walletService;

	@Autowired
	FakeCurrentCryptoDataService cryptoService;

	@Autowired
	AssetService assetService;

	@Autowired
	UtenteService utenteService;

	public Operazione create(OperazionePayload body) {

		FakeCurrentCryptoData crypto = cryptoService.findBySimbolo(body.getSimboloCrypto());
		Wallet wallet = walletService.findById(body.getIdWallet());

		TipoOperazione tipoOperazione = TipoOperazione.valueOf(body.getTipoOperazione());
		double quantita = body.getQuantita();

		LocalDateTime dataOperazione = LocalDateTime.now(ZoneId.of("Europe/Rome"));

		Operazione operazione = new Operazione();

		if (tipoOperazione == TipoOperazione.SELL) {
			vendiCriptovaluta(crypto, wallet, quantita);
			operazione.setPrezzoVendita(crypto.getPrezzo());

		} else {
			acquistaCriptovaluta(crypto, wallet, quantita);
			operazione.setPrezzoAcquisto(crypto.getPrezzo());
		}

		operazione.setWallet(wallet);
		operazione.setCrypto(crypto);
		operazione.setTipoOperazione(tipoOperazione);
		operazione.setDataOperazione(dataOperazione);
		operazione.setQuantita(quantita);
		return operazioneRepo.save(operazione);
	}

	private void vendiCriptovaluta(FakeCurrentCryptoData crypto, Wallet wallet, double quantita) {
		List<Asset> assetDetenuti = wallet.getListaAsset();
		Asset assetTrovato = null;

		for (Asset asset : assetDetenuti) {
			if (asset.getCrypto().equals(crypto)) {
				assetTrovato = asset;
				break;
			}
		}

		if (assetTrovato == null) {
			throw new BadRequestException("Non detieni " + crypto.getNome() + " nel wallet!");
		}

		if (quantita > assetTrovato.getQuantita()) {
			throw new BadRequestException("Quantità di asset disponibile insufficiente per la vendita.");
		}

		double nuovaQuantita = assetTrovato.getQuantita() - quantita;
		if (nuovaQuantita == 0.0) {
			double valoreCriptovaluta = quantita * crypto.getPrezzo();
			double nuovoSaldo = wallet.getSaldoDisponibile() + valoreCriptovaluta;
			walletService.findByIdAndUpadate(wallet.getId().toString(), nuovoSaldo);
			assetService.findByIdAndDelete(assetTrovato.getId().toString());
		} else {
			double valoreCriptovaluta = nuovaQuantita * crypto.getPrezzo();
			double nuovoSaldo = wallet.getSaldoDisponibile() + valoreCriptovaluta;
			assetService.findByIdAndUpadate(assetTrovato.getId().toString(), wallet, crypto, nuovaQuantita);
			walletService.findByIdAndUpadate(wallet.getId().toString(), nuovoSaldo);
		}
	}

	private void acquistaCriptovaluta(FakeCurrentCryptoData crypto, Wallet wallet, double quantita) {
		List<Asset> assetDetenuti = wallet.getListaAsset();
		Asset assetTrovato = null;

		for (Asset asset : assetDetenuti) {
			if (asset.getCrypto().equals(crypto)) {
				assetTrovato = asset;
				break;
			}
		}

		if (assetTrovato == null) {
			double nuovoSaldo = wallet.getSaldoDisponibile() - (quantita * crypto.getPrezzo());
			if (nuovoSaldo < 0) {
				throw new BadRequestException("Saldo disponibile insufficiente per l'acquisto.");
			}

			assetService.create(wallet, crypto, quantita);
			walletService.findByIdAndUpadate(wallet.getId().toString(), nuovoSaldo);
		} else {
			double nuovaQuantita = assetTrovato.getQuantita() + quantita;
			double valoreCriptovaluta = quantita * crypto.getPrezzo();
			double nuovoSaldo = wallet.getSaldoDisponibile() - valoreCriptovaluta;

			assetService.findByIdAndUpadate(assetTrovato.getId().toString(), wallet, crypto, nuovaQuantita);
			walletService.findByIdAndUpadate(wallet.getId().toString(), nuovoSaldo);
		}
	}

	public List<Operazione> find() {
		return operazioneRepo.findAll();
	}

	public Page<Operazione> findAll(int page, String ordinamento) {
		Pageable pagina = PageRequest.of(page, 10, Sort.by(ordinamento));
		return operazioneRepo.findAll(pagina);
	}

	public Operazione findById(String id) {
		return operazioneRepo.findById(UUID.fromString(id))
				.orElseThrow(() -> new NotFoundException("Operazione con id: " + id + " non trovata!"));
	}

	public void findByIdAndDelete(String id) {
		Operazione o = findById(id);
		operazioneRepo.delete(o);
	}

	public Page<Operazione> findByWallet(int page, String ordinamento, String idWallet) {
		Wallet w = walletService.findById(idWallet);
		Pageable pagina = PageRequest.of(page, 10, Sort.by(ordinamento));
		return operazioneRepo.findByWallet(pagina, w);
	}

}
