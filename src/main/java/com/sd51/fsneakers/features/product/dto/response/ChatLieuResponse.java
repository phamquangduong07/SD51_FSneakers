package com.sd51.fsneakers.features.product.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatLieuResponse {

    UUID id;

    String ma;

    String ten;

    Integer trangThai;

    LocalDateTime ngayTao;

    LocalDateTime ngaySua;

}
