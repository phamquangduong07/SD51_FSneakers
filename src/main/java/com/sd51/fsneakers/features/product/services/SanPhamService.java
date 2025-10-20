package com.sd51.fsneakers.features.product.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sd51.fsneakers.features.product.entity.SanPham;

public interface SanPhamService {

    List<SanPham> getAllSanPham();

    SanPham findByMa(String maSanPham);

    SanPham createSanPham(SanPham sanPham);

    SanPham updateSanPham(String ma, SanPham updateSanPham);

    SanPham deleteSanPham(String maSanPham);

    Page<SanPham> getAllSanPhamPage(Pageable pageable);

    Page<SanPham> searchSanPham(String keyword, Integer trangThai, Pageable pageable);

}
