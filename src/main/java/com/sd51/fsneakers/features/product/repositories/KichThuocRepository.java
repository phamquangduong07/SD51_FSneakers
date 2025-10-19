package com.sd51.fsneakers.features.product.repositories;

import com.sd51.fsneakers.features.product.entity.KichThuoc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface KichThuocRepository extends JpaRepository<KichThuoc, UUID> {
}
