package it.epicode.entities.crypto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.entities.crypto.FakeCurrentCryptoData;

public interface FakeCurrentCryptoDataRepository extends JpaRepository<FakeCurrentCryptoData, String> {

}
