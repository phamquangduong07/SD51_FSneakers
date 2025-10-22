package com.sd51.fsneakers.features.product.dto.response;

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
public class SanPhamChiTietResponse {

    UUID id;

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
    UUID chatLieu;

    UUID danhMuc;

    UUID deGiay;

    UUID hangGiay;

    UUID kichThuoc;

    UUID mauSac;

    UUID sanPham;

    // image url
    List<String> hinhAnhSanPhams;
}
