package com.sd51.fsneakers.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "thong_bao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ThongBao extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    private UUID id;

    // 0 = hệ thống, 1 = đơn hàng, 2 = khuyến mãi, 3 = thanh toán, ...
    @Column(name = "loai", nullable = false)
    private Integer loai;

    // Nếu thông báo liên quan đến hóa đơn
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hoa_don_id")
    private HoaDon hoaDon;

    // Nếu thông báo gửi cho khách hàng cụ thể
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "khach_hang_id")
    private KhachHang khachHang;

    @Column(name = "noi_dung", length = 255)
    private String noiDung;

    // 0 = chưa đọc, 1 = đã đọc, 2 = ẩn / xóa
    @Column(name = "trang_thai", nullable = false)
    private Integer trangThai;

    @Column(name = "ngay_tao")
    private LocalDateTime ngayTao;

    @Column(name = "ngay_sua")
    private LocalDateTime ngaySua;
}