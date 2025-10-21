package com.sd51.fsneakers.features.order.repositories;

import com.sd51.fsneakers.features.order.entity.ThanhToan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ThanhToanRepository extends JpaRepository<ThanhToan, UUID> {
    ThanhToan findByHoaDon_Id(UUID hoaDonId);
}
