package com.sd51.fsneakers.features.product.services;

import java.util.List;


import com.sd51.fsneakers.features.product.dto.request.DanhMucRequest;
import com.sd51.fsneakers.features.product.dto.response.DanhMucResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sd51.fsneakers.features.product.entity.DanhMuc;
import org.springframework.web.client.HttpClientErrorException;

public interface DanhMucService {

    List<DanhMucResponse> getAllDanhMuc();

    Page<DanhMucResponse> getAllDanhMucPage(Pageable pageable);

    Page<DanhMucResponse> searchDanhMuc(String keyword, Integer trangThai, Pageable pageable);

    DanhMuc findByMa(String ma);

    DanhMucResponse createDanhMuc(DanhMucRequest request);

    DanhMucResponse updateDanhMucByMa(String ma, DanhMucRequest request);

    void deleteDanhMuc(String ma);







}
