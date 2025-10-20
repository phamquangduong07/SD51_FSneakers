package com.sd51.fsneakers.features.product.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sd51.fsneakers.features.product.entity.KichThuoc;

public interface KichThuocService {

    List<KichThuoc> getAllKichThuoc();

    KichThuoc findByMa(String maKichThuoc);

    KichThuoc createKichThuoc(KichThuoc kichThuoc);

    KichThuoc updateKichThuoc(String ma, KichThuoc kichThuocUpdate);

    KichThuoc deleteKichThuoc(String maKichThuoc);

    Page<KichThuoc> getAllKichThuocPage(Pageable pageable);

    Page<KichThuoc> searchKichThuoc(String keyword, Integer trangThai, Pageable pageable);
}
