package com.sd51.fsneakers.features.order.mapper;

import com.sd51.fsneakers.features.order.dtos.response.HoaDonChiTietResponse;
import com.sd51.fsneakers.features.order.entity.HoaDonChiTiet;

import java.math.BigDecimal;

public class HoaDonChiTietMapper {

    public static HoaDonChiTietResponse toHoaDonChiTietResponse(HoaDonChiTiet hoaDonChiTiet) {
        BigDecimal thanhTien = hoaDonChiTiet.getDonGia().multiply(BigDecimal.valueOf(hoaDonChiTiet.getSoLuong()));
        return HoaDonChiTietResponse.builder()
                .tenSanPham(STR."\{hoaDonChiTiet.getChiTietSanPham().getSanPham().getTen()}(\{hoaDonChiTiet.getChiTietSanPham().getKichThuoc().getTen()})")
                .soLuong(hoaDonChiTiet.getSoLuong())
                .donGia(hoaDonChiTiet.getDonGia())
                .thanhTien(thanhTien)
                .hoaDonChiTietId(hoaDonChiTiet.getId())
                .soLuongTon(hoaDonChiTiet.getChiTietSanPham().getSoLuong())
                .build();
    }
}
