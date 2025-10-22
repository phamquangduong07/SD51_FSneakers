package com.sd51.fsneakers.features.mapper;


import com.sd51.fsneakers.features.product.dto.request.HangGiayRequest;
import com.sd51.fsneakers.features.product.dto.response.HangGiayResponse;
import com.sd51.fsneakers.features.product.entity.HangGiay;

public class HangGiayMapper {

    public static HangGiay toEntity(HangGiayRequest req) {
        if (req == null) return null;

        HangGiay entity = new HangGiay();
        entity.setMa(req.getMa());
        entity.setTen(req.getTen());
        entity.setTrangThai(req.getTrangThai());

        return entity;
    }

    public static HangGiayResponse toResponse(HangGiay hangGiay) {
        if (hangGiay == null) return null;

        HangGiayResponse res = new HangGiayResponse();
        res.setId(hangGiay.getId());
        res.setMa(hangGiay.getMa());
        res.setTen(hangGiay.getTen());
        res.setTrangThai(hangGiay.getTrangThai());
        res.setNgayTao(hangGiay.getNgayTao());
        res.setNgaySua(hangGiay.getNgaySua());

        return res;
    }

    public static void toUpdate(HangGiay hangGiay, HangGiayRequest req) {
        if (hangGiay == null || req == null) return;

        if (hangGiay.getMa() == null) hangGiay.setMa(req.getMa());
        if (req.getTen() != null) hangGiay.setTen(req.getTen());
        if (req.getTrangThai() != null) hangGiay.setTrangThai(req.getTrangThai());
    }

}
