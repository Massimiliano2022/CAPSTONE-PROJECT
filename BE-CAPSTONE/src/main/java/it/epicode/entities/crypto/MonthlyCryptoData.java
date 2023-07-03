package it.epicode.entities.crypto;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class MonthlyCryptoData {

	@Id
	@GeneratedValue
	private int id;
	private String simbolo;
	private String nome;
	private double chiusuraPrezzo;
	private double variazionePrezzo;
	private LocalDate data;

	public MonthlyCryptoData(String simbolo, String nome, double chiusuraPrezzo, double variazionePrezzo,
			LocalDate data) {
		setSimbolo(simbolo);
		setNome(nome);
		setChiusuraPrezzo(chiusuraPrezzo);
		setVariazionePrezzo(variazionePrezzo);
		setData(data);
	}

}
