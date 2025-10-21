package com.sd51.fsneakers.features.order.dtos.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HoaDonChiTietResponse {
    private UUID hoaDonChiTietId;
    private String tenSanPham;
    private Integer soLuong;
    private BigDecimal donGia;
    private BigDecimal thanhTien;
    private Integer soLuongTon;
}
