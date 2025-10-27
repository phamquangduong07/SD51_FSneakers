package com.sd51.fsneakers.features.order.dto.response;

import com.sd51.fsneakers.features.product.entity.SanPhamChiTiet;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HoaDonResponseDTO {
    private String ma;
    private String tenKhachHang;
    private BigDecimal giaGoc;
    private BigDecimal giamGia;
    private BigDecimal thanhTien;
    private LocalDateTime ngayTao;
    private String maNhanVien;
    private List<SanPhamChiTiet> chiTietSanPham;
}
