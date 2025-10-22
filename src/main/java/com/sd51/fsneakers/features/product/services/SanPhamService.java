package com.sd51.fsneakers.features.product.services;

import java.util.List;

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

    SanPhamResponse createSanPham(SanPhamRequest request);

    SanPhamResponse updateSanPham(String ma, SanPhamRequest request);

    void deleteSanPham(String maSanPham);


}
