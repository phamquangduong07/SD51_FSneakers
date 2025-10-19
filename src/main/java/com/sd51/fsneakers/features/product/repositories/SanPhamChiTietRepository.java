package com.sd51.fsneakers.features.product.repositories;

import com.sd51.fsneakers.features.product.entity.SanPhamChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SanPhamChiTietRepository extends JpaRepository<SanPhamChiTiet, UUID> {
}
