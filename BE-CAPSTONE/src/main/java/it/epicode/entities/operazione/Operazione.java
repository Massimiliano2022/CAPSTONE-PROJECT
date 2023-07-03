package it.epicode.entities.operazione;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import it.epicode.entities.crypto.FakeCurrentCryptoData;
import it.epicode.entities.wallet.Wallet;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Operazione {

	@Id
	@GeneratedValue
	private UUID id;
	@ManyToOne
	@JsonBackReference
	private Wallet wallet;
	@ManyToOne
	@JsonManagedReference
	private FakeCurrentCryptoData crypto;
	@Enumerated(EnumType.STRING)
	private TipoOperazione tipoOperazione;
	double quantita;
	private double prezzoAcquisto;
	private double prezzoVendita;
	private LocalDateTime dataAperturaOperazione;
	private LocalDateTime dataChiusuraOperazione;

	public Operazione(Wallet wallet, FakeCurrentCryptoData crypto, TipoOperazione tipoOperazione, double quantita) {
		setWallet(wallet);
		setCrypto(crypto);
		setTipoOperazione(tipoOperazione);
		setQuantita(quantita);
	}

}
