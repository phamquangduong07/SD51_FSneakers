package com.sd51.fsneakers.features.product.repositories;

import com.sd51.fsneakers.features.product.entity.SanPhamChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SanPhamChiTietRepository extends JpaRepository<SanPhamChiTiet, UUID> {
    SanPhamChiTiet findByMa(String ma);
}
