package com.sd51.fsneakers.features.product.repositories;

import com.sd51.fsneakers.features.product.entity.HangGiay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HangGiayRepository extends JpaRepository<HangGiay, UUID> {
    HangGiay findByMa(String ma);
}
