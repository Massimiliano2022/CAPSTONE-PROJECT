package it.epicode.entities.operazione.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OperazionePayload {
	private String idWallet;
	private String simboloCrypto;
	private String tipoOperazione;
	private double quantita;

}
