package com.sd51.fsneakers.features.mapper;


import com.sd51.fsneakers.features.product.dto.request.MauSacRequest;
import com.sd51.fsneakers.features.product.dto.response.MauSacResponse;
import com.sd51.fsneakers.features.product.entity.MauSac;

public class MauSacMapper {

    public static MauSac toEntity(MauSacRequest req) {
        if (req == null) return null;

        MauSac entity = new MauSac();
        entity.setMa(req.getMa());
        entity.setTen(req.getTen());
        entity.setTrangThai(req.getTrangThai());

        return entity;
    }
    public static MauSacResponse toResponse(MauSac mauSac) {
        if (mauSac == null) return null;

        MauSacResponse res = new MauSacResponse();
        res.setId(mauSac.getId());
        res.setMa(mauSac.getMa());
        res.setTen(mauSac.getTen());
        res.setTrangThai(mauSac.getTrangThai());
        res.setNgayTao(mauSac.getNgayTao());
        res.setNgaySua(mauSac.getNgaySua());

        return res;
    }
    public static void toUpdate(MauSac mauSac, MauSacRequest req) {
        if (mauSac == null || req == null) return;

        if (mauSac.getMa() == null) mauSac.setMa(req.getMa());
        if (req.getTen() != null) mauSac.setTen(req.getTen());
        if (req.getTrangThai() != null) mauSac.setTrangThai(req.getTrangThai());
    }
}
