package com.sd51.fsneakers.features.product.services;

import java.util.List;
import java.util.UUID;

import com.sd51.fsneakers.features.product.dto.request.SanPhamRequest;
import com.sd51.fsneakers.features.product.dto.response.SanPhamResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sd51.fsneakers.features.product.entity.SanPham;

public interface SanPhamService {

    List<SanPhamResponse> getAllSanPham();

    Page<SanPhamResponse> getAllSanPhamPage(Pageable pageable);

    Page<SanPhamResponse> searchSanPham(String keyword, Integer trangThai, Pageable pageable);

    SanPham findByMa(String maSanPham);

    SanPham findById(UUID id);

    SanPhamResponse createSanPham(SanPhamRequest request);

    SanPhamResponse updateSanPham(UUID id, SanPhamRequest request);

    SanPhamResponse deleteSanPham(UUID id);


}
