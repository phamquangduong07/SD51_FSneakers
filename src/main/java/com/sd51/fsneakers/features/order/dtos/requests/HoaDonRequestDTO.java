package com.sd51.fsneakers.features.order.dtos.requests;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class HoaDonRequestDTO {
    private String maHoaDon;
    private String ghiChu;

    private BigDecimal giaGoc;           // Tổng giá chưa giảm
    private BigDecimal giaGiamGia;       // Tổng tiền giảm (nếu có voucher)
    private BigDecimal thanhTien;        // Tổng tiền thanh toán cuối cùng
    private BigDecimal tienVanChuyen = BigDecimal.ZERO; // 0 vì tại quầy không ship

    private String phuongThuc;           // Tiền mặt / Chuyển khoản / Kết hợp
    private BigDecimal soTienThanhToan;  // Tổng tiền khách trả

    private String loaiHoaDon = "TAI_QUAY"; // Gắn cứng loại hóa đơn
    private Integer trangThaiHoaDon = 0;    // Trạng thái khởi tạo

    private LocalDateTime ngayMua = LocalDateTime.now();

    // ====== Thông tin liên kết ======
    private UUID nhanVienId;
    private UUID khachHangId;  // Có thể null nếu khách vãng lai
    private UUID voucherId;    // Nếu có voucher

    // ====== Thông tin người nhận (vì tại quầy có thể nhập nhanh) ======
    private String tenNguoiNhan;
    private String soDienThoai;
    private String email;
    private String diaChi;
}
