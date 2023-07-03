package it.epicode.entities.wallet;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import it.epicode.entities.crypto.FakeCurrentCryptoData;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Asset {
	@Id
	@GeneratedValue
	private UUID id;
	@ManyToOne
	@JsonBackReference
	private Wallet wallet;
	@ManyToOne
	@JsonManagedReference
	private FakeCurrentCryptoData crypto;
	private double quantita;

	public Asset(Wallet wallet, FakeCurrentCryptoData crypto, double quantita) {
		setWallet(wallet);
		setCrypto(crypto);
		setQuantita(quantita);
	}
}
