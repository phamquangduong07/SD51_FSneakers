package com.sd51.fsneakers.features.order.dtos.response;

import com.sd51.fsneakers.features.user.entity.KhachHang;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HoaDonResponse {

    private UUID id;
    private String ma;
    private String loaiHoaDon; // "TAI_QUAY"
    private BigDecimal giaGoc;
    private BigDecimal giaGiamGia;
    private BigDecimal thanhTien;
    private BigDecimal soTienThanhToan;
    private String trangThaiHoaDon;
    private String phuongThuc;
    private LocalDateTime ngayTao;
    private LocalDateTime ngaySua;
    private String maNhanVien;
    private String  maKhachHang;
    private List<HoaDonChiTietResponse> chiTietList;
}
