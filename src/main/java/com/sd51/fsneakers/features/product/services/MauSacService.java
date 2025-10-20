package com.sd51.fsneakers.features.product.services;

import java.util.List;

import com.sd51.fsneakers.features.product.entity.MauSac;

public interface MauSacService {

    List<MauSac> getAllMauSac();

    MauSac findByMa(String maMauSac);

    MauSac createMauSac(MauSac mauSac);

    MauSac updateMauSac(String ma, MauSac mauSacUpdate);

    MauSac deleteMauSac(String maMauSac);

}
