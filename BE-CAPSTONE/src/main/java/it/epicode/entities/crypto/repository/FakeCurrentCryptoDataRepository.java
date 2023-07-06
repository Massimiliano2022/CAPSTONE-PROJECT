package it.epicode.entities.crypto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.entities.crypto.FakeCurrentCryptoData;

public interface FakeCurrentCryptoDataRepository extends JpaRepository<FakeCurrentCryptoData, Integer> {
	Optional<FakeCurrentCryptoData> findBySimbolo(String simbolo);
}
