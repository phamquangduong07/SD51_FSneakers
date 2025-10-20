package com.sd51.fsneakers.features.product.services;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sd51.fsneakers.features.product.entity.HinhAnhSanPham;
import org.springframework.web.multipart.MultipartFile;

public interface HinhAnhSanPhamService {
    List<HinhAnhSanPham> getAllHinhAnhBySanPham();

    HinhAnhSanPham findByMa(String ma);

    HinhAnhSanPham createHinhAnhSanPham(HinhAnhSanPham hinhAnhSanPham);

    HinhAnhSanPham updateHinhAnhSanPham(String ma, HinhAnhSanPham hinhAnhSanPhamUpdate);

    HinhAnhSanPham deleteHinhAnhSanPham(String ma);

    Page<HinhAnhSanPham> getAllHinhAnhSanPhamPage(Pageable pageable);

    Page<HinhAnhSanPham> searchHinhAnhSanPham(String keyword, Integer trangThai, Pageable pageable);

    List<HinhAnhSanPham> updloadImage(UUID chiTietSanPhamId, List<MultipartFile> files) throws IOException;

    List<HinhAnhSanPham> getImagesByChiTietId(UUID chiTietId);
}
