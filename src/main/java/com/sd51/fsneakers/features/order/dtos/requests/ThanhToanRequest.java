package com.sd51.fsneakers.features.order.dtos.requests;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ThanhToanRequest {
                 // id của hóa đơn cần thanh toán
          // tổng tiền thanh toán
    private BigDecimal tienMat;          // số tiền khách trả bằng tiền mặt
    private BigDecimal chuyenKhoan;      // số tiền khách trả bằng chuyển khoản / thẻ

    private String phuongThucVnp;
    // mã VNPay / mã giao dịch (nếu có)
    private String ghiChu;
}
