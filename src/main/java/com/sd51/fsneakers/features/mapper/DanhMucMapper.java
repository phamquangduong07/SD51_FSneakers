package com.sd51.fsneakers.features.mapper;


import com.sd51.fsneakers.features.product.dto.request.DanhMucRequest;
import com.sd51.fsneakers.features.product.dto.response.DanhMucResponse;
import com.sd51.fsneakers.features.product.entity.DanhMuc;

public class DanhMucMapper {

    public static DanhMuc toEntity(DanhMucRequest req) {
        if (req == null) return null;

        DanhMuc entity = new DanhMuc();
        entity.setMa(req.getMa());
        entity.setTen(req.getTen());
        entity.setTrangThai(req.getTrangThai());

        return entity;
    }

    public static DanhMucResponse toResponse(DanhMuc danhMuc) {
        if (danhMuc == null) return null;

        DanhMucResponse res = new DanhMucResponse();
        res.setId(danhMuc.getId());
        res.setMa(danhMuc.getMa());
        res.setTen(danhMuc.getTen());
        res.setTrangThai(danhMuc.getTrangThai());
        res.setNgayTao(danhMuc.getNgayTao());
        res.setNgaySua(danhMuc.getNgaySua());

        return res;
    }

    public static void toUpdate(DanhMuc danhMuc, DanhMucRequest req) {
        if (danhMuc == null || req == null) return;

        if (danhMuc.getMa() == null) danhMuc.setMa(req.getMa());
        if (req.getTen() != null) danhMuc.setTen(req.getTen());
        if (req.getTrangThai() != null) danhMuc.setTrangThai(req.getTrangThai());
    }
}
