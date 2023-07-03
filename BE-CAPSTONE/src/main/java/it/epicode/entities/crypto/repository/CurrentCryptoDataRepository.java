package it.epicode.entities.crypto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.entities.crypto.CurrentCryptoData;

public interface CurrentCryptoDataRepository extends JpaRepository<CurrentCryptoData, String> {

}
