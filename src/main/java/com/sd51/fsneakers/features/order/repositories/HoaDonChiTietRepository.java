package com.sd51.fsneakers.features.order.repositories;

import com.sd51.fsneakers.features.order.dtos.response.HoaDonChiTietResponse;
import com.sd51.fsneakers.features.order.dtos.response.HoaDonChiTietResponse;
import com.sd51.fsneakers.features.order.entity.HoaDon;
import com.sd51.fsneakers.features.order.entity.HoaDonChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, UUID> {
    @Query("SELECT SUM(h.donGia * h.soLuong) FROM HoaDonChiTiet h WHERE h.hoaDon.id = :hoaDonId")
    BigDecimal tinhTongTien(@Param("hoaDonId") UUID hoaDonId);

    List<HoaDonChiTiet> findHoaDonChiTietByHoaDon(HoaDon hoaDon);

    List<HoaDonChiTiet> findHoaDonChiTietByHoaDon_Id(UUID hoaDonId);

    List<HoaDonChiTietResponse> findAllByHoaDon_Id(UUID hoaDonId);

    Optional<HoaDonChiTiet> findHoaDonChiTietByHoaDon_IdAndChiTietSanPham_Id(UUID id, UUID id1);

    @Modifying
    @Transactional
    void deleteHoaDonChiTietByHoaDon_Id(UUID hoaDonId);

    void deleteHoaDonChiTietById(UUID id);
}


