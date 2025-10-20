package com.sd51.fsneakers.features.product.repositories;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.sd51.fsneakers.features.product.entity.HinhAnhSanPham;

@Repository
public interface HinhAnhSanPhamRepository extends JpaRepository<HinhAnhSanPham, UUID> {
    HinhAnhSanPham findByMa(String ma);
}
