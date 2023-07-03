package it.epicode.entities.utente;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@JsonIgnoreProperties({ "password", "username", "accountNonExpired", "accountNonLocked", "enabled",
		"credentialsNonExpired", "authorities" })

public class Utente implements UserDetails {

	@Id
	@GeneratedValue
	private UUID id;
	private String nome;
	private String cognome;
	private String email;
	private String password;
	private LocalDate dataRegistrazione;
	@Enumerated(EnumType.STRING)
	private Role ruolo = Role.USER;
	private boolean isEnabled;
	private boolean isCredentialsNonExpired;
	private boolean isAccountNonExpired;
	private boolean isAccountNonLocked;

	public Utente(String nome, String cognome, String email, String password) {
		setNome(nome);
		setCognome(cognome);
		setEmail(email);
		setPassword(password);
		setDataRegistrazione(LocalDate.now());
		setEnabled(true);
		setAccountNonExpired(true);
		setCredentialsNonExpired(true);
		setAccountNonLocked(true);
	}

	@Override
	public String getUsername() {
		return null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return List.of(new SimpleGrantedAuthority(ruolo.name()));
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.isEnabled;
	}
}
