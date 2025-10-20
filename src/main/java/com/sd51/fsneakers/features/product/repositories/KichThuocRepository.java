package com.sd51.fsneakers.features.product.repositories;

import com.sd51.fsneakers.features.product.entity.KichThuoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface KichThuocRepository extends JpaRepository<KichThuoc, UUID> {
    KichThuoc findByMa(String ma);
}
