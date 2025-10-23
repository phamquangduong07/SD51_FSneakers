package com.sd51.fsneakers.features.product.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SanPhamChiTietRequest {

    String ma;

    String maQr;

    BigDecimal giaBan;

    BigDecimal giaNhap;

    Integer soLuong;

    String moTa;

    Integer trangThai;

    String nguoiTao;

    String nguoiSua;

    // Join cac table
    UUID chatLieuId;

    UUID danhMucId;

    UUID deGiayId;

    UUID hangGiayId;

    UUID kichThuocId;

    UUID mauSacId;

    UUID sanPhamId;

    // image url
    List<String> hinhAnhSanPhamUrls;
}
