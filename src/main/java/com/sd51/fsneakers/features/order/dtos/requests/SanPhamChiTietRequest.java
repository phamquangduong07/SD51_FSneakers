package com.sd51.fsneakers.features.order.dtos.requests;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SanPhamChiTietRequest {
    private String maCTSP;
    private Integer soLuong;
}
