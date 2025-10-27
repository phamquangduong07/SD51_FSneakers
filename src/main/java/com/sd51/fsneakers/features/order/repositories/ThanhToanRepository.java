package com.sd51.fsneakers.features.order.repositories;

import com.sd51.fsneakers.features.order.entity.ThanhToan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ThanhToanRepository extends JpaRepository<ThanhToan, UUID> {

}
