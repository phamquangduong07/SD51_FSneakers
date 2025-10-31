package com.sd51.fsneakers.features.order.dtos.response;

import com.sd51.fsneakers.features.product.entity.SanPhamChiTiet;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
