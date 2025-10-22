package com.sd51.fsneakers.features.mapper;

import com.sd51.fsneakers.features.product.dto.request.SanPhamRequest;
import com.sd51.fsneakers.features.product.dto.response.SanPhamResponse;
import com.sd51.fsneakers.features.product.entity.SanPham;

public class SanPhamMapper {
    public static SanPham toEntity(SanPhamRequest req) {
        if (req == null) return null;

        SanPham entity = new SanPham();
        entity.setMa(req.getMa());
        entity.setTen(req.getTen());
        entity.setTrangThai(req.getTrangThai());

        return entity;
    }
    public static SanPhamResponse toResponse(SanPham sanPham) {
        if (sanPham == null) return null;

        SanPhamResponse res = new SanPhamResponse();
        res.setId(sanPham.getId());
        res.setMa(sanPham.getMa());
        res.setTen(sanPham.getTen());
        res.setTrangThai(sanPham.getTrangThai());
        res.setNgayTao(sanPham.getNgayTao());
        res.setNgaySua(sanPham.getNgaySua());

        return res;
    }
    public static void toUpdate(SanPham sanPham, SanPhamRequest req) {
        if (sanPham == null || req == null) return;

        if (sanPham.getMa() == null) sanPham.setMa(req.getMa());
        if (req.getTen() != null) sanPham.setTen(req.getTen());
        if (req.getTrangThai() != null) sanPham.setTrangThai(req.getTrangThai());
    }
}
