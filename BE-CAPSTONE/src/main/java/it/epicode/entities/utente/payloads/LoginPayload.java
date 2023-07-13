package it.epicode.entities.utente.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginPayload {
	@NotBlank(message = "Inserire indirizzo e-mail!")
	@Email(message = "Non hai inserito un indirizzo email valido")
	private String email;
	@NotNull(message = "Inserire password!")
	@Size(min = 3, max = 30, message = "Password min 3 caratteri, massimo 30")
	private String password;
}
