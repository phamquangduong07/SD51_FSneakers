package com.sd51.fsneakers.features.product.services;

import java.util.List;

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

    SanPhamChiTietResponse createSanPhamChiTiet(SanPhamChiTietRequest request);

    SanPhamChiTietResponse updateSanPhamChiTiet(String ma, SanPhamChiTietRequest request);

    void deleteSanPhamChiTiet(String maSanPhamChiTiet);


}
