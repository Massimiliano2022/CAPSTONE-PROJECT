package it.epicode.entities.wallet.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.entities.crypto.FakeCurrentCryptoData;
import it.epicode.entities.wallet.Asset;

public interface AssetRepository extends JpaRepository<Asset, UUID> {
	Optional<Asset> findByCrypto(FakeCurrentCryptoData crypto);
}
