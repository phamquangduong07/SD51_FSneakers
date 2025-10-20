package com.sd51.fsneakers.features.product.repositories;

import com.sd51.fsneakers.features.product.entity.DeGiay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DeGiayRepository extends JpaRepository<DeGiay, UUID> {
    DeGiay findByMa(String maDeGiay);
}
