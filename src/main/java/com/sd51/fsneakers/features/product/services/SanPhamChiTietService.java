package com.sd51.fsneakers.features.product.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sd51.fsneakers.features.product.entity.DanhMuc;
import com.sd51.fsneakers.features.product.entity.SanPhamChiTiet;

public interface SanPhamChiTietService {

    List<SanPhamChiTiet> getAllSanPhamChiTiet();

    SanPhamChiTiet findByMa(String maSanPhamChiTiet);

    SanPhamChiTiet createSanPhamChiTiet(SanPhamChiTiet sanPhamChiTiet);

    SanPhamChiTiet updateSanPhamChiTiet(String ma, SanPhamChiTiet sanPhamChiTietUpdate);

    SanPhamChiTiet deleteSanPhamChiTiet(String maSanPhamChiTiet);

    Page<SanPhamChiTiet> getAllSanPhamChiTietPage(Pageable pageable);

    Page<SanPhamChiTiet> searchSanPhamChiTiet(String keyword, Integer trangThai, Pageable pageable);

}
