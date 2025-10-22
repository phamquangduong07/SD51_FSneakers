package com.sd51.fsneakers.features.mapper;

import com.sd51.fsneakers.features.product.dto.request.SanPhamChiTietRequest;
import com.sd51.fsneakers.features.product.dto.response.SanPhamChiTietResponse;
import com.sd51.fsneakers.features.product.entity.*;

import java.util.stream.Collectors;


public class SanPhamChiTietMapper {

    public static SanPhamChiTiet toEntity(SanPhamChiTietRequest req) {
        if (req == null) return null;

        SanPhamChiTiet entity = new SanPhamChiTiet();
        entity.setMa(req.getMa());
        entity.setMaQr(req.getMaQr());
        entity.setGiaNhap(req.getGiaNhap());
        entity.setGiaBan(req.getGiaBan());
        entity.setSoLuong(req.getSoLuong());
        entity.setMoTa(req.getMoTa());
        entity.setTrangThai(req.getTrangThai());
        entity.setNguoiTao(req.getNguoiTao());
        entity.setNguoiSua(req.getNguoiSua());

        // Map các entity con theo ID
        if (req.getChatLieuId() != null) {
            entity.setChatLieu(new ChatLieu(req.getChatLieuId()));
        }
        if (req.getDanhMucId() != null) {
            entity.setDanhMuc(new DanhMuc(req.getDanhMucId()));
        }
        if (req.getDeGiayId() != null) {
            entity.setDeGiay(new DeGiay(req.getDeGiayId()));
        }
        if (req.getHangGiayId() != null) {
            entity.setHangGiay(new HangGiay(req.getHangGiayId()));
        }
        if (req.getKichThuocId() != null) {
            entity.setKichThuoc(new KichThuoc(req.getKichThuocId()));
        }
        if (req.getMauSacId() != null) {
            entity.setMauSac(new MauSac(req.getMauSacId()));
        }
        if (req.getSanPhamId() != null) {
            entity.setSanPham(new SanPham(req.getSanPhamId()));
        }

        // Map hình ảnh
        if (req.getHinhAnhSanPhams() != null) {
            entity.setHinhAnhSanPhams(req.getHinhAnhSanPhams().stream()
                    .map(url -> HinhAnhSanPham.builder()
                            .url(url)
                            .trangThai(1)
                            .build())
                    .collect(Collectors.toList()));
        }

        return entity;
    }

    public static SanPhamChiTietResponse toResponse(SanPhamChiTiet entity) {
        if (entity == null) return null;

        SanPhamChiTietResponse res = new SanPhamChiTietResponse();
        res.setId(entity.getId());
        res.setMa(entity.getMa());
        res.setMaQr(entity.getMaQr());
        res.setGiaNhap(entity.getGiaNhap());
        res.setGiaBan(entity.getGiaBan());
        res.setSoLuong(entity.getSoLuong());
        res.setMoTa(entity.getMoTa());
        res.setTrangThai(entity.getTrangThai());
        res.setNguoiTao(entity.getNguoiTao());
        res.setNguoiSua(entity.getNguoiSua());

        // Map id của các entity con
        if (entity.getChatLieu() != null) res.setChatLieu(entity.getChatLieu().getId());
        if (entity.getDanhMuc() != null) res.setDanhMuc(entity.getDanhMuc().getId());
        if (entity.getDeGiay() != null) res.setDeGiay(entity.getDeGiay().getId());
        if (entity.getHangGiay() != null) res.setHangGiay(entity.getHangGiay().getId());
        if (entity.getKichThuoc() != null) res.setKichThuoc(entity.getKichThuoc().getId());
        if (entity.getMauSac() != null) res.setMauSac(entity.getMauSac().getId());
        if (entity.getSanPham() != null) res.setSanPham(entity.getSanPham().getId());

        // Map hình ảnh
        if (entity.getHinhAnhSanPhams() != null) {
            res.setHinhAnhSanPhams(entity.getHinhAnhSanPhams().stream()
                    .map(HinhAnhSanPham::getUrl)
                    .collect(Collectors.toList()));
        }

        return res;
    }

    public static void toUpdate(SanPhamChiTiet entity, SanPhamChiTietRequest req) {
        if (req == null || entity == null) return;

        // Cập nhật có kiểm null
        if (req.getGiaBan() != null) entity.setGiaBan(req.getGiaBan());
        if (req.getSoLuong() != null) entity.setSoLuong(req.getSoLuong());
        if (req.getMoTa() != null) entity.setMoTa(req.getMoTa());
        if (req.getTrangThai() != null) entity.setTrangThai(req.getTrangThai());

        // ... và cập nhật các liên kết tương tự như ở trên
    }
}
