package com.sd51.fsneakers.User.Repository;

import com.sd51.fsneakers.User.Entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface KhachHangRepo extends JpaRepository<KhachHang,UUID>{


}
