package com.sd51.fsneakers.features.product.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HinhAnhSanPhamResponse {

    UUID id;

    String ma;

    String ten;

    String url;

    Integer trangThai;

    LocalDateTime ngayTao;

    LocalDateTime ngaySua;

    UUID chiTietSanPham;

}
