package com.sd51.fsneakers.features.product.repositories;

import com.sd51.fsneakers.features.product.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, UUID> {
    SanPham findByMa(String ma);
}
