package com.sd51.fsneakers.features.order.repositories;

import com.sd51.fsneakers.features.order.entity.HoaDonChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, UUID> {
}
