package com.sd51.fsneakers.features.product.repositories;

import com.sd51.fsneakers.features.product.entity.HangGiay;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HangGiayRepository extends JpaRepository<HangGiay, UUID> {
    HangGiay findByMa(String ma);

     //  Phân trang cơ bản (Spring Data tự sinh)
    @Query("SELECT dm FROM HangGiay dm")
    Page<HangGiay> getAllPage(Pageable pageable);

    //  Tìm kiếm theo keyword + trạng thái (ví dụ tìm theo tên danh mục)
    @Query("""
            SELECT dm FROM HangGiay dm
            WHERE (:keyword IS NULL OR :keyword = '' OR dm.ten LIKE %:keyword%)
              AND (:trangThai IS NULL OR dm.trangThai = :trangThai)
            """)
    Page<HangGiay> searchHangGiay(
            @Param("keyword") String keyword,
            @Param("trangThai") Integer trangThai,
            Pageable pageable);
}
