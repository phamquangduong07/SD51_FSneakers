package com.sd51.fsneakers.features.product.repositories;

import com.sd51.fsneakers.features.product.entity.HangGiay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HangGiayRepository extends JpaRepository<HangGiay, UUID> {
}
