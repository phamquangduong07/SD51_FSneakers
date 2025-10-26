package com.sd51.fsneakers.User.Repository;

import com.sd51.fsneakers.User.Entity.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NguoiDungRepo extends JpaRepository<NguoiDung, UUID> {
}
