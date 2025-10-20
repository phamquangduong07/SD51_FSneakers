package com.sd51.fsneakers.features.product.repositories;

import com.sd51.fsneakers.features.product.entity.DanhMuc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DanhMucRepository extends JpaRepository<DanhMuc, UUID> {
    DanhMuc findByMa(String maDanhMuc);
}
