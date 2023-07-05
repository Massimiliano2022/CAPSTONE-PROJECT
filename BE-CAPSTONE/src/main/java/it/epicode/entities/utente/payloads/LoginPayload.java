package it.epicode.entities.utente.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginPayload {
	@Email(message = "Non hai inserito un indirizzo email valido")
	private String email;
	@NotNull(message = "La password è obbligatoria")
	@Size(min = 3, max = 30, message = "Password min 3 caratteri, massimo 30")
	private String password;
}
