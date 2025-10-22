package com.sd51.fsneakers.features.product.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeGiayResponse {

    UUID id;

    String ma;

    String ten;

    Integer trangThai;

    LocalDateTime ngayTao;

    LocalDateTime ngaySua;

}
