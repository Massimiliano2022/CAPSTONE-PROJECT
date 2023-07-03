package it.epicode.entities.crypto;

import java.time.LocalDateTime;
import java.time.ZoneId;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class CurrentCryptoData {

	@Id
	private String simbolo;
	private String nome;
	private double prezzo;
	private LocalDateTime timestamp;
	private double percententuale_variazione_1h;

	public CurrentCryptoData(String simbolo, String nome, double prezzo, double percententuale_variazione_1h) {
		setSimbolo(simbolo);
		setNome(nome);
		setPrezzo(prezzo);
		setTimestamp(LocalDateTime.now(ZoneId.of("Europe/Rome")));
		setPercententuale_variazione_1h(percententuale_variazione_1h);
	}

}
