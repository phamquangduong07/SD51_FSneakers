package com.sd51.fsneakers.entity;
import jakarta.persistence.*;
import lombok.*;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Entity
@Table(name = "hoa_don")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HoaDon {
    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    private UUID id;

    @Column(name = "ma", nullable = false, unique = true, length = 255)
    private String ma;

    @Column(name = "qr_code", length = 500)
    private String qrCode;

    @Column(name = "ten_nguoi_nhan", nullable = false, length = 100)
    private String tenNguoiNhan;

    @Column(name = "so_dien_thoai", nullable = false, length = 20)
    private String soDienThoai;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "dia_chi", nullable = false, length = 255)
    private String diaChi;

    @Column(name = "ghi_chu", length = 500)
    private String ghiChu;

    @Column(name = "gia_goc", precision = 18, scale = 0)
    private BigDecimal giaGoc = BigDecimal.ZERO;

    @Column(name = "gia_giam_gia", precision = 18, scale = 0)
    private BigDecimal giaGiamGia = BigDecimal.ZERO;

    @Column(name = "thanh_tien", precision = 18, scale = 0)
    private BigDecimal thanhTien = BigDecimal.ZERO;

    @Column(name = "tien_van_chuyen", precision = 18, scale = 0)
    private BigDecimal tienVanChuyen = BigDecimal.ZERO;

    @Column(name = "loai_hoa_don", nullable = false, length = 50)
    private String loaiHoaDon; // ONLINE hoặc TAI_QUAY

    @Column(name = "ngay_mua")
    private LocalDateTime ngayMua;

    @Column(name = "ngay_du_kien_nhan")
    private LocalDateTime ngayDuKienNhan;

    @Column(name = "ngay_nhan_hang")
    private LocalDateTime ngayNhanHang;

    @Column(name = "phuong_thuc", length = 50)
    private String phuongThuc; // tiền mặt, chuyển khoản, v.v.

    @Column(name = "so_tien_thanh_toan", precision = 18, scale = 0)
    private BigDecimal soTienThanhToan;

    @Column(name = "trang_thai_hoa_don")
    private Integer trangThaiHoaDon = 0;

    // Liên kết tới khách hàng
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "khach_hang_id", nullable = false)
    private KhachHang khachHang;

    // Nhân viên xử lý (có thể null nếu khách hàng tự đặt online)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nhan_vien_id")
    private NhanVien nhanVien;

    // Voucher áp dụng (nếu có)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voucher_id")
    private Voucher voucher;

    // Quan hệ 1-nhiều với chi tiết hóa đơn
    @OneToMany(mappedBy = "hoaDon", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HoaDonChiTiet> chiTietList = new ArrayList<>();
}
