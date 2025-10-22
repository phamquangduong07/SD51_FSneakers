package com.sd51.fsneakers.features.product.services;

import java.util.List;

import com.sd51.fsneakers.features.product.dto.request.KichThuocRequest;
import com.sd51.fsneakers.features.product.dto.response.KichThuocResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sd51.fsneakers.features.product.entity.KichThuoc;

public interface KichThuocService {

    List<KichThuocResponse> getAllKichThuoc();

    Page<KichThuocResponse> getAllKichThuocPage(Pageable pageable);

    Page<KichThuocResponse> searchKichThuoc(String keyword, Integer trangThai, Pageable pageable);

    KichThuoc findByMa(String maKichThuoc);

    KichThuocResponse createKichThuoc(KichThuocRequest request);

    KichThuocResponse updateKichThuoc(String ma, KichThuocRequest request);

    void deleteKichThuoc(String maKichThuoc);


}
