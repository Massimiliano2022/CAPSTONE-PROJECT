package it.epicode.entities.crypto.payloads;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CriptovalutaUpdatePayload {

	private String nome;
	private double prezzo;
	private LocalDateTime timestamp;
	private double percententuale_variazione_1h;
}
