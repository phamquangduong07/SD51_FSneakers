package com.sd51.fsneakers.features.order.entity;

import com.sd51.fsneakers.commons.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "hoa_don")
@AllArgsConstructor
@NoArgsConstructor
public class HoaDon extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    private UUID id;

    @Column(name = "ma", nullable = false, unique = true, length = 255)
    private String ma;

    @Nationalized
    @Column(name = "qr_code", length = 500)
    private String qrCode;

    @Nationalized
    @Column(name = "ten_nguoi_nhan", length = 100)
    private String tenNguoiNhan;

    @Column(name = "so_dien_thoai", length = 20)
    private String soDienThoai;

    @Column(name = "email", length = 100)
    private String email;

    @Nationalized
    @Column(name = "dia_chi", nullable = false, length = 255)
    private String diaChi;

    @Nationalized
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

    @Nationalized
    @Column(name = "loai_hoa_don", nullable = false, length = 50)
    private String loaiHoaDon;

    @Column(name = "ngay_mua")
    private LocalDateTime ngayMua;

    @Column(name = "ngay_du_kien_nhan")
    private LocalDateTime ngayDuKienNhan;

    @Column(name = "ngay_nhan_hang")
    private LocalDateTime ngayNhanHang;

    @Nationalized
    @Column(name = "phuong_thuc", length = 50)
    private String phuongThuc;

    @Column(name = "so_tien_thanh_toan", precision = 18, scale = 0)
    private BigDecimal soTienThanhToan;

    @Column(name = "trang_thai_hoa_don", columnDefinition = "int default 0")
    private Integer trangThaiHoaDon = 0;



    @Column(name = "nguoi_sua", length = 255)
    private String nguoiSua;

    @Column(name = "nguoi_tao", length = 255)
    private String nguoiTao;

    // ==== Quan hệ khóa ngoại ====

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "khach_hang_id")
    private KhachHang khachHang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nhan_vien_id")
    private NhanVien nhanVien;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voucher_id")
    private Voucher voucher;
}