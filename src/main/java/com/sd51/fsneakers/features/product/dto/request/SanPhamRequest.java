package com.sd51.fsneakers.features.product.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SanPhamRequest {

    String ma;

    String ten;

    String moTa;

    Integer trangThai;


}
