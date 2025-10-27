package com.sd51.fsneakers.features.product.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MauSacRequest {

    String ma;

    String ten;

    Integer trangThai;

}
