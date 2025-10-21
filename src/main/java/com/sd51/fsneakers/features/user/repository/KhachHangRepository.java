package com.sd51.fsneakers.features.user.repository;

import com.sd51.fsneakers.features.user.entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Optional;
import java.util.UUID;

public interface KhachHangRepository extends JpaRepository<KhachHang, UUID> {


    @Query("SELECT k FROM KhachHang k JOIN k.nguoiDung n WHERE n.soDienThoai = :phone")
    Optional<KhachHang> findByNguoiDungSoDienThoai(@Param("phone") String phone);
}