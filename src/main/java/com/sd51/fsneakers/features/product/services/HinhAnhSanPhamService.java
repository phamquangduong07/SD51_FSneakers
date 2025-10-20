package com.sd51.fsneakers.features.product.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sd51.fsneakers.features.product.entity.HinhAnhSanPham;

public interface HinhAnhSanPhamService {
    List<HinhAnhSanPham> getAllHinhAnhBySanPham();

    HinhAnhSanPham findByMa(String ma);

    HinhAnhSanPham createHinhAnhSanPham(HinhAnhSanPham hinhAnhSanPham);

    HinhAnhSanPham updateHinhAnhSanPham(String ma, HinhAnhSanPham hinhAnhSanPhamUpdate);

    HinhAnhSanPham deleteHinhAnhSanPham(String ma);

    Page<HinhAnhSanPham> getAllHinhAnhSanPhamPage(Pageable pageable);

    Page<HinhAnhSanPham> searchHinhAnhSanPham(String keyword, Integer trangThai, Pageable pageable);
}
