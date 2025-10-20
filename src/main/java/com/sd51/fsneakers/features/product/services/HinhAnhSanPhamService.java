package com.sd51.fsneakers.features.product.services;

import java.util.List;

import com.sd51.fsneakers.features.product.entity.HinhAnhSanPham;

public interface HinhAnhSanPhamService {
    List<HinhAnhSanPham> getAllHinhAnhBySanPham();

    HinhAnhSanPham findByMa(String ma);

    HinhAnhSanPham createHinhAnhSanPham(HinhAnhSanPham hinhAnhSanPham);

    HinhAnhSanPham updateHinhAnhSanPham(String ma, HinhAnhSanPham hinhAnhSanPhamUpdate);

    HinhAnhSanPham deleteHinhAnhSanPham(String ma);
}
