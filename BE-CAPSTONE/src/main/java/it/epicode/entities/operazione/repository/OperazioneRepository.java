package it.epicode.entities.operazione.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.github.javafaker.Crypto;

import it.epicode.entities.operazione.Operazione;
import it.epicode.entities.operazione.TipoOperazione;
import it.epicode.entities.wallet.Wallet;

public interface OperazioneRepository extends JpaRepository<Operazione, UUID> {

	Page<Operazione> findByWallet(Pageable pagina, Wallet wallet);

	Page<Operazione> findByWalletAndTipoOperazione(Pageable pagina, Wallet wallet, TipoOperazione tipoOperazione);

	Page<Operazione> findByWalletAndCrypto(Pageable pagina, Wallet wallet, Crypto crypto);

}
