package com.sd51.fsneakers.features.product.repositories;

import com.sd51.fsneakers.features.product.entity.DeGiay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeGiayRepository extends JpaRepository<DeGiay, UUID> {
}
