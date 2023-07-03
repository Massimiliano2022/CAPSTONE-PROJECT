package it.epicode.entities.utente.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UtenteUpdatePayload {
	private String nome;
	private String cognome;
	private String email;
}
