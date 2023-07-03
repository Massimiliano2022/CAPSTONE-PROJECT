package it.epicode.entities.wallet.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.entities.utente.Utente;
import it.epicode.entities.wallet.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {

	Optional<Wallet> findByUtente(Utente utente);

}
