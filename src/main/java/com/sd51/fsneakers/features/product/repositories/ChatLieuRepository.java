package com.sd51.fsneakers.features.product.repositories;

import com.sd51.fsneakers.features.product.entity.ChatLieu;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChatLieuRepository extends JpaRepository<ChatLieu, UUID> {
    ChatLieu findByMa(String ma);

    //  Phân trang cơ bản (Spring Data tự sinh)
    @Query("SELECT c FROM ChatLieu c")
    Page<ChatLieu> getAllPage(Pageable pageable);

    //  Tìm kiếm theo keyword + trạng thái (ví dụ tìm theo tên chất liệu)
    @Query("""
            SELECT c FROM ChatLieu c
            WHERE (:keyword IS NULL OR :keyword = '' OR c.ten LIKE %:keyword%)
              AND (:trangThai IS NULL OR c.trangThai = :trangThai)
            """)
    Page<ChatLieu> searchChatLieu(
            @Param("keyword") String keyword,
            @Param("trangThai") Integer trangThai,
            Pageable pageable);
}
