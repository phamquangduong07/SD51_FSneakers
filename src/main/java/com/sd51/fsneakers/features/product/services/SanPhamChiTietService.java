package com.sd51.fsneakers.features.product.services;

import java.util.List;
import java.util.UUID;

import com.sd51.fsneakers.features.product.dto.request.SanPhamChiTietRequest;
import com.sd51.fsneakers.features.product.dto.response.SanPhamChiTietResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sd51.fsneakers.features.product.entity.SanPhamChiTiet;

public interface SanPhamChiTietService {

    List<SanPhamChiTietResponse> getAllSanPhamChiTiet();

    Page<SanPhamChiTietResponse> getAllSanPhamChiTietPage(Pageable pageable);

    Page<SanPhamChiTietResponse> searchSanPhamChiTiet(String keyword, Integer trangThai, Pageable pageable);

    SanPhamChiTiet findByMa(String maSanPhamChiTiet);

    SanPhamChiTiet findById(UUID id);

    SanPhamChiTietResponse getById(UUID id);

    SanPhamChiTietResponse createSanPhamChiTiet(SanPhamChiTietRequest request);

    SanPhamChiTietResponse updateSanPhamChiTiet(UUID id, SanPhamChiTietRequest request);

    SanPhamChiTietResponse deleteSanPhamChiTiet(UUID id);

}
