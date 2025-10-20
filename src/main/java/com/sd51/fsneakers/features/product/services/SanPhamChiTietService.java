package com.sd51.fsneakers.features.product.services;

import java.util.List;

import com.sd51.fsneakers.features.product.entity.SanPhamChiTiet;

public interface SanPhamChiTietService {

    List<SanPhamChiTiet> getAllSanPhamChiTiet();

    SanPhamChiTiet findByMa(String maSanPhamChiTiet);

    SanPhamChiTiet createSanPhamChiTiet(SanPhamChiTiet sanPhamChiTiet);

    SanPhamChiTiet updateSanPhamChiTiet(String ma, SanPhamChiTiet sanPhamChiTietUpdate);

    SanPhamChiTiet deleteSanPhamChiTiet(String maSanPhamChiTiet);

}
