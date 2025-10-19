package com.sd51.fsneakers.features.product.repositories;

import com.sd51.fsneakers.features.product.entity.DanhMuc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DanhMucRepository extends JpaRepository<DanhMuc, UUID> {
    DanhMuc findByMa(String maDanhMuc);
}
