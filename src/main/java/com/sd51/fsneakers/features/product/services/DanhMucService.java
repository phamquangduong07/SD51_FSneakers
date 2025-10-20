package com.sd51.fsneakers.features.product.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sd51.fsneakers.features.product.entity.DanhMuc;

public interface DanhMucService {

    List<DanhMuc> getAllDanhMuc();

    DanhMuc createDanhMuc(DanhMuc danhMuc);

    DanhMuc findByMa(String ma);

    DanhMuc updateDanhMucByMa(String ma, DanhMuc danhMucNew);

    DanhMuc deleteDanhMuc(String ma);

    Page<DanhMuc> getAllDanhMucPage(Pageable pageable);

    Page<DanhMuc> searchDanhMuc(String keyword, Integer trangThai, Pageable pageable);

}
