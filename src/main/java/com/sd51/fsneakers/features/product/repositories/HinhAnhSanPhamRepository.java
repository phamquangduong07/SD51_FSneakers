package com.sd51.fsneakers.features.product.repositories;


import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sd51.fsneakers.features.product.entity.HinhAnhSanPham;


@Repository
public interface HinhAnhSanPhamRepository extends JpaRepository<HinhAnhSanPham, UUID> {
    HinhAnhSanPham findByMa(String ma);

     //  Phân trang cơ bản (Spring Data tự sinh)
    @Query("SELECT dm FROM HinhAnhSanPham dm")
    Page<HinhAnhSanPham> getAllPage(Pageable pageable);

    //  Tìm kiếm theo keyword + trạng thái (ví dụ tìm theo tên danh mục)
    @Query("""
            SELECT dm FROM HinhAnhSanPham dm
            WHERE (:keyword IS NULL OR :keyword = '' OR dm.ten LIKE %:keyword%)
              AND (:trangThai IS NULL OR dm.trangThai = :trangThai)
            """)
    Page<HinhAnhSanPham> searchHinhAnhSanPham(
            @Param("keyword") String keyword,
            @Param("trangThai") Integer trangThai,
            Pageable pageable);

    List<HinhAnhSanPham> findByChiTietSanPham_Id(UUID chiTietSanPhamId);
}
