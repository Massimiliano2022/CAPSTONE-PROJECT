package it.epicode.entities.operazione.payloads;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OperazionePayload {
	@NotNull(message = "L'id del wallet è obbligatorio")
	private String idWallet;
	@NotNull(message = "Il simbolo crypto è obbligatorio")
	private String simboloCrypto;
	@NotNull(message = "Il tipo operazione è obbligatorio")
	private String tipoOperazione;
	@Min(value = 1, message = "La quantità deve essere maggiore di zero")
	private double quantita;

}
