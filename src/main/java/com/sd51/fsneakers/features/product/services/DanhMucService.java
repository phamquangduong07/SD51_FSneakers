package com.sd51.fsneakers.features.product.services;

import java.util.List;
import java.util.UUID;


import com.sd51.fsneakers.features.product.dto.request.DanhMucRequest;
import com.sd51.fsneakers.features.product.dto.response.DanhMucResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sd51.fsneakers.features.product.entity.DanhMuc;

public interface DanhMucService {

    List<DanhMucResponse> getAllDanhMuc();

    Page<DanhMucResponse> getAllDanhMucPage(Pageable pageable);

    Page<DanhMucResponse> searchDanhMuc(String keyword, Integer trangThai, Pageable pageable);

    DanhMuc findByMa(String ma);

    DanhMuc findById(UUID id);

    DanhMucResponse createDanhMuc(DanhMucRequest request);

    DanhMucResponse updateDanhMucByMa(UUID id, DanhMucRequest request);

    DanhMucResponse deleteDanhMuc(UUID id);







}
