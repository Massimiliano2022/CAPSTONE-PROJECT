package it.epicode.entities.wallet.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WalletUpdatePayload {
	private double saldoDisponibile;
}
