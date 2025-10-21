package com.sd51.fsneakers.features.user.repository;

import com.sd51.fsneakers.features.user.entity.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NguoiDungRepository extends JpaRepository<NguoiDung, UUID> {
}