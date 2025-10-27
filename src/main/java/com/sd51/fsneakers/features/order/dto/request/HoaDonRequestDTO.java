package com.sd51.fsneakers.features.order.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HoaDonRequestDTO {

    String maHoaDon;

    String ghiChu;

    BigDecimal giaGoc;           // Tổng giá chưa giảm

    BigDecimal giaGiamGia;       // Tổng tiền giảm (nếu có voucher)

    BigDecimal thanhTien;        // Tổng tiền thanh toán cuối cùng

    BigDecimal tienVanChuyen = BigDecimal.ZERO; // 0 vì tại quầy không ship

    String phuongThuc;           // Tiền mặt / Chuyển khoản / Kết hợp

    BigDecimal soTienThanhToan;  // Tổng tiền khách trả

    String loaiHoaDon = "TAI_QUAY"; // Gắn cứng loại hóa đơn

    Integer trangThaiHoaDon = 0;    // Trạng thái khởi tạo

    LocalDateTime ngayMua = LocalDateTime.now();

    // ====== Thông tin liên kết ======
    UUID nhanVienId;

    UUID khachHangId;  // Có thể null nếu khách vãng lai

    UUID voucherId;    // Nếu có voucher

    // ====== Thông tin người nhận (vì tại quầy có thể nhập nhanh) ======
    String tenNguoiNhan;

    String soDienThoai;

    String email;

    String diaChi;
}
