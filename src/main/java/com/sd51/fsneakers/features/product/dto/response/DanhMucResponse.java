package com.sd51.fsneakers.features.product.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DanhMucResponse {

    UUID id;

    String ma;

    String ten;

    Integer trangThai;

    LocalDateTime ngayTao;

    LocalDateTime ngaySua;

}
