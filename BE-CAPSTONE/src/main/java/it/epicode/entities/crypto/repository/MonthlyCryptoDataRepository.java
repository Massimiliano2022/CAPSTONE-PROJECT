package it.epicode.entities.crypto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.entities.crypto.MonthlyCryptoData;

public interface MonthlyCryptoDataRepository extends JpaRepository<MonthlyCryptoData, String> {

	List<MonthlyCryptoData> findBySimbolo(String simbolo);

}
