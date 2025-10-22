package com.sd51.fsneakers.features.mapper;

import com.sd51.fsneakers.features.product.dto.request.HinhAnhSanPhamRequest;
import com.sd51.fsneakers.features.product.dto.response.HinhAnhSanPhamResponse;
import com.sd51.fsneakers.features.product.entity.HinhAnhSanPham;
import com.sd51.fsneakers.features.product.entity.SanPhamChiTiet;

public class HinhAnhSanPhamMapper {


    public static HinhAnhSanPham toEntity(HinhAnhSanPhamRequest req){
        if (req == null) return null;

        HinhAnhSanPham entity = new HinhAnhSanPham();
        entity.setMa(req.getMa());
        entity.setTen(req.getTen());
        entity.setUrl(req.getUrl());
        entity.setTrangThai(req.getTrangThai());

        // Map các entity con theo ID
        if (req.getChiTietSanPhamId() != null) {
            entity.setChiTietSanPham(new SanPhamChiTiet(req.getChiTietSanPhamId()));
        }
        return entity;
    }


    public static HinhAnhSanPhamResponse toResponse(HinhAnhSanPham hinhAnhSanPham){
        if (hinhAnhSanPham == null) return null;

        HinhAnhSanPhamResponse res = new HinhAnhSanPhamResponse();
        res.setId(hinhAnhSanPham.getId());
        res.setMa(hinhAnhSanPham.getMa());
        res.setTen(hinhAnhSanPham.getTen());
        res.setUrl(hinhAnhSanPham.getUrl());
        res.setTrangThai(hinhAnhSanPham.getTrangThai());
        res.setNgayTao(hinhAnhSanPham.getNgayTao());
        res.setNgaySua(hinhAnhSanPham.getNgaySua());

        // Map id của các entity con
        if (hinhAnhSanPham.getChiTietSanPham() != null) res.setChiTietSanPhamId(hinhAnhSanPham.getChiTietSanPham().getId());

        return res;
    }


    public static void toUpdate(HinhAnhSanPham existing, HinhAnhSanPhamRequest req){
        if (req == null || existing == null) return;

        // Cập nhật có kiểm null
        if (req.getMa() != null) existing.setMa(req.getMa());
        if (req.getTen() != null) existing.setTen(req.getTen());
        if (req.getUrl() != null) existing.setUrl(req.getUrl());
        if (req.getTrangThai() != null) existing.setTrangThai(req.getTrangThai());
    }
}
