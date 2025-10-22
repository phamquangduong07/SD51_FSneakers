package com.sd51.fsneakers.features.mapper;

import com.sd51.fsneakers.features.product.dto.request.DeGiayRequest;
import com.sd51.fsneakers.features.product.dto.response.DeGiayResponse;
import com.sd51.fsneakers.features.product.entity.DeGiay;

public class DeGiayMapper {
    public static DeGiay toEntity(DeGiayRequest req) {
        if (req == null) return null;
        DeGiay entity = new DeGiay();
        entity.setMa(req.getMa());
        entity.setTen(req.getTen());
        entity.setTrangThai(req.getTrangThai());

        return entity;
    }
    public static DeGiayResponse toResponse(DeGiay deGiay) {
        if (deGiay == null) return null;

        DeGiayResponse res = new DeGiayResponse();
        res.setId(deGiay.getId());
        res.setMa(deGiay.getMa());
        res.setTen(deGiay.getTen());
        res.setTrangThai(deGiay.getTrangThai());
        res.setNgayTao(deGiay.getNgayTao());
        res.setNgaySua(deGiay.getNgaySua());

        return res;
    }
    public static void toUpdate(DeGiay deGiay, DeGiayRequest req) {
        if (deGiay == null || req == null) return;

        if (deGiay.getMa() == null) deGiay.setMa(req.getMa());
        if (req.getTen() != null) deGiay.setTen(req.getTen());
        if (req.getTrangThai() != null) deGiay.setTrangThai(req.getTrangThai());
    }
}
