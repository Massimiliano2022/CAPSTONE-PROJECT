package it.epicode.entities.operazione.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.epicode.entities.crypto.FakeCurrentCryptoData;
import it.epicode.entities.operazione.Operazione;
import it.epicode.entities.operazione.TipoOperazione;
import it.epicode.entities.wallet.Wallet;

public interface OperazioneRepository extends JpaRepository<Operazione, UUID> {

	Page<Operazione> findByWallet(Pageable pagina, Wallet wallet);

	Page<Operazione> findByWalletAndTipoOperazione(Pageable pagina, Wallet wallet, TipoOperazione tipoOperazione);

	Page<Operazione> findByWalletAndCrypto(Pageable pagina, Wallet wallet, FakeCurrentCryptoData crypto);

	Page<Operazione> findByWalletAndTipoOperazioneAndCrypto(Pageable pagina, Wallet wallet,
			TipoOperazione tipoOperazione, FakeCurrentCryptoData crypto);

	@Query("SELECT o FROM Operazione o WHERE " + "(o.wallet = :wallet) "
			+ "AND (:tipoOperazione IS NULL OR o.tipoOperazione = :tipoOperazione) "
			+ "AND (:crypto IS NULL OR o.crypto = :crypto) "
			+ "AND (:startDate IS NULL OR o.dataOperazione >= CAST(:startDate AS timestamp)) "
			+ "AND (:endDate IS NULL OR o.dataOperazione <= CAST(:endDate AS timestamp))")
	Page<Operazione> findOperazioniByCustomFilters(Pageable pagina, @Param("wallet") Wallet wallet,
			@Param("tipoOperazione") TipoOperazione tipoOperazione, @Param("crypto") FakeCurrentCryptoData crypto,
			@Param("startDate") String startDate, @Param("endDate") String endDate);

}
