package com.sd51.fsneakers.features.product.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sd51.fsneakers.features.product.entity.MauSac;

public interface MauSacService {

    List<MauSac> getAllMauSac();

    MauSac findByMa(String maMauSac);

    MauSac createMauSac(MauSac mauSac);

    MauSac updateMauSac(String ma, MauSac mauSacUpdate);

    MauSac deleteMauSac(String maMauSac);

    Page<MauSac> getAllMauSacPage(Pageable pageable);

    Page<MauSac> searchMauSac(String keyword, Integer trangThai, Pageable pageable);

}
