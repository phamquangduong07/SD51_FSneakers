package com.sd51.fsneakers.features.product.repositories;

import com.sd51.fsneakers.features.product.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SanPhamRepository extends JpaRepository<SanPham, UUID> {
}
