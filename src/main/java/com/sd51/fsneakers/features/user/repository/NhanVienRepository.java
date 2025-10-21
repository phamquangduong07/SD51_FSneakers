package com.sd51.fsneakers.features.user.repository;

import com.sd51.fsneakers.features.user.entity.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface NhanVienRepository extends JpaRepository<NhanVien, UUID> {
    Optional<NhanVien> findNhanVienByNguoiDung_Ma(String nguoiDungMa);
}