package it.epicode.entities.wallet;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import it.epicode.entities.operazione.Operazione;
import it.epicode.entities.utente.Utente;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Wallet {

	@Id
	@GeneratedValue
	private UUID id;
	@OneToOne
	private Utente utente;
	@OneToMany(mappedBy = "wallet")
	@JsonManagedReference
	private List<Asset> listaAsset;
	@OneToMany(mappedBy = "wallet")
	@JsonManagedReference
	private List<Operazione> listaOperazioni;
	private double saldoDisponibile;
	private String valuta;

	public Wallet(Utente utente, double saldoDisponibile) {
		setUtente(utente);
		setSaldoDisponibile(saldoDisponibile);
		setValuta("$");
	}

}
