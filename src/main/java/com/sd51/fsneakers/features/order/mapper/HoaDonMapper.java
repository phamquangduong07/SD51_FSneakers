package com.sd51.fsneakers.features.order.mapper;

import com.sd51.fsneakers.features.order.dtos.response.HoaDonChiTietResponse;
import com.sd51.fsneakers.features.order.dtos.response.HoaDonResponse;
import com.sd51.fsneakers.features.order.entity.HoaDon;
import com.sd51.fsneakers.features.order.entity.HoaDonChiTiet;
import com.sd51.fsneakers.features.order.entity.ThanhToan;
import com.sd51.fsneakers.features.order.repositories.ThanhToanRepository;
import com.sd51.fsneakers.features.order.services.HoaDonService;
import com.sd51.fsneakers.features.order.services.impl.HoaDonServiceImpl;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class HoaDonMapper {


    public static HoaDonResponse toHoaDonResponse(HoaDon hoaDon) {

        return   HoaDonResponse.builder()
                .id(hoaDon.getId())
                .ma(hoaDon.getMa())
                .trangThaiHoaDon(hoaDon.getStatus().getDescription())
                .maNhanVien(hoaDon.getNhanVien().getNguoiDung().getMa())
                .ngayTao(hoaDon.getNgayTao())
                .ngaySua(hoaDon.getNgaySua())
                .loaiHoaDon(hoaDon.getLoaiHoaDon())
                .phuongThuc(hoaDon.getPhuongThuc())
                .soTienThanhToan(hoaDon.getSoTienThanhToan())
                .giaGoc(hoaDon.getThanhTien())
                .giaGiamGia(hoaDon.getGiaGiamGia())
                .thanhTien(hoaDon.getThanhTien())
                .maKhachHang(hoaDon.getKhachHang() != null ? hoaDon.getKhachHang().getNguoiDung().getMa() : null)
                .chiTietList(
                        Optional.ofNullable(hoaDon.getChiTietList())
                                .orElse(Collections.emptyList())
                                .stream()
                                .map(HoaDonChiTietMapper::toHoaDonChiTietResponse)
                                .toList()
                )
                .build();

    }
}
