package com.sd51.fsneakers.User.Repository;


import com.sd51.fsneakers.User.Entity.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NhanVienRepo extends JpaRepository<NhanVien, UUID> {


}
