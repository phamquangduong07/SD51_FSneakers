package com.sd51.fsneakers.features.mapper;


import com.sd51.fsneakers.features.product.dto.request.KichThuocRequest;
import com.sd51.fsneakers.features.product.dto.response.KichThuocResponse;
import com.sd51.fsneakers.features.product.entity.KichThuoc;

public class KichThuocMapper {
    public static KichThuoc toEntity(KichThuocRequest req) {
        if (req == null) return null;

        KichThuoc entity = new KichThuoc();
        entity.setMa(req.getMa());
        entity.setTen(req.getTen());
        entity.setTrangThai(req.getTrangThai());

        return entity;
    }
    public static KichThuocResponse toResponse(KichThuoc kichThuoc) {
        if (kichThuoc == null) return null;

        KichThuocResponse res = new KichThuocResponse();
        res.setId(kichThuoc.getId());
        res.setMa(kichThuoc.getMa());
        res.setTen(kichThuoc.getTen());
        res.setTrangThai(kichThuoc.getTrangThai());
        res.setNgayTao(kichThuoc.getNgayTao());
        res.setNgaySua(kichThuoc.getNgaySua());

        return res;
    }
    public static void toUpdate(KichThuoc kichThuoc, KichThuocRequest req) {
        if (kichThuoc == null || req == null) return;

        if (kichThuoc.getMa() == null) kichThuoc.setMa(req.getMa());
        if (req.getTen() != null) kichThuoc.setTen(req.getTen());
        if (req.getTrangThai() != null) kichThuoc.setTrangThai(req.getTrangThai());
    }
}
