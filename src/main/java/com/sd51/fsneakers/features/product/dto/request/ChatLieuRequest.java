package com.sd51.fsneakers.features.product.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatLieuRequest {

    String ma;

    String ten;

    Integer trangThai;

}
