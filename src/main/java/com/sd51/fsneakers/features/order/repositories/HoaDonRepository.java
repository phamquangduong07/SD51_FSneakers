package com.sd51.fsneakers.features.order.repositories;

import com.sd51.fsneakers.features.order.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HoaDonRepository extends JpaRepository<HoaDon, UUID> {
}
