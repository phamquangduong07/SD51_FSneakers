package com.sd51.fsneakers.features.product.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HinhAnhSanPhamRequest {

    String ma;

    String ten;

    String url;

    Integer trangThai;

    UUID chiTietSanPhamId;

}
