package com.sd51.fsneakers.features.product.services;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import com.sd51.fsneakers.features.product.dto.request.HinhAnhSanPhamRequest;
import com.sd51.fsneakers.features.product.dto.response.HinhAnhSanPhamResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sd51.fsneakers.features.product.entity.HinhAnhSanPham;
import org.springframework.web.multipart.MultipartFile;

public interface HinhAnhSanPhamService {

    List<HinhAnhSanPhamResponse> getAllHinhAnhBySanPham();

    Page<HinhAnhSanPhamResponse> getAllHinhAnhSanPhamPage(Pageable pageable);

    Page<HinhAnhSanPhamResponse> searchHinhAnhSanPham(String keyword, Integer trangThai, Pageable pageable);

    List<HinhAnhSanPham> getImagesByChiTietId(UUID chiTietId);

    HinhAnhSanPham findByMa(String ma);

    HinhAnhSanPham findById(UUID id);

    HinhAnhSanPhamResponse createHinhAnhSanPham(HinhAnhSanPhamRequest request);

    HinhAnhSanPhamResponse updateHinhAnhSanPham(UUID id, HinhAnhSanPhamRequest request);

    HinhAnhSanPhamResponse deleteHinhAnhSanPham(UUID id);

    List<HinhAnhSanPham> updloadImage(UUID chiTietSanPhamId, List<MultipartFile> files) throws IOException;

    HinhAnhSanPham uploadAndSaveImage(MultipartFile file, String ma, String ten, Integer trangThai, UUID chiTietSanPhamId) throws IOException;
}
