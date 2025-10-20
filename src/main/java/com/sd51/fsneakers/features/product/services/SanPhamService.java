package com.sd51.fsneakers.features.product.services;

import java.util.List;

import com.sd51.fsneakers.features.product.entity.SanPham;

public interface SanPhamService {

    List<SanPham> getAllSanPham();

    SanPham findByMa(String maSanPham);

    SanPham createSanPham(SanPham sanPham);

    SanPham updateSanPham(String ma, SanPham updateSanPham);

    SanPham deleteSanPham(String maSanPham);
    
}
