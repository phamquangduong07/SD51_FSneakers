package com.sd51.fsneakers.features.product.services;

import java.util.List;

import com.sd51.fsneakers.features.product.dto.request.MauSacRequest;
import com.sd51.fsneakers.features.product.dto.response.MauSacResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sd51.fsneakers.features.product.entity.MauSac;

public interface MauSacService {

    List<MauSacResponse> getAllMauSac();

    Page<MauSacResponse> getAllMauSacPage(Pageable pageable);

    Page<MauSacResponse> searchMauSac(String keyword, Integer trangThai, Pageable pageable);

    MauSac findByMa(String maMauSac);

    MauSacResponse createMauSac(MauSacRequest request);

    MauSacResponse updateMauSac(String ma, MauSacRequest request);

    void deleteMauSac(String maMauSac);


}
