package it.epicode.entities.crypto.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.epicode.entities.crypto.FakeCurrentCryptoData;

public interface FakeCurrentCryptoDataRepository extends JpaRepository<FakeCurrentCryptoData, Integer> {

	Optional<FakeCurrentCryptoData> findBySimbolo(String simbolo);

	@Query("SELECT crypto FROM FakeCurrentCryptoData crypto "
			+ "WHERE UPPER(crypto.nome) LIKE CONCAT(UPPER(:query), '%') "
			+ "OR UPPER(crypto.simbolo) LIKE CONCAT(UPPER(:query), '%')")
	Page<FakeCurrentCryptoData> searchByNameOrSymbol(Pageable pagina, @Param("query") String query);

}
