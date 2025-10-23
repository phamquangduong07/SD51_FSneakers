package com.sd51.fsneakers.features.product.services;

import java.util.List;
import java.util.UUID;

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

    KichThuoc findById(UUID id);

    KichThuocResponse createKichThuoc(KichThuocRequest request);

    KichThuocResponse updateKichThuoc(UUID id, KichThuocRequest request);

    KichThuocResponse deleteKichThuoc(UUID id);


}
