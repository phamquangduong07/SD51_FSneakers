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

    @Column(name = "ma", nullable = false)
    private String ma;

    @Nationalized
    @Column(name = "qr_code", length = 500)
    private String qrCode;

    @Nationalized
    @Column(name = "ten_nguoi_nhan", nullable = false, length = 100)
    private String tenNguoiNhan;

    @Nationalized
    @Column(name = "so_dien_thoai", nullable = false, length = 20)
    private String soDienThoai;

    @Nationalized
    @Column(name = "email", length = 100)
    private String email;

    @Nationalized
    @Column(name = "dia_chi", nullable = false)
    private String diaChi;

    @Nationalized
    @Column(name = "ghi_chu", length = 500)
    private String ghiChu;

    @ColumnDefault("0")
    @Column(name = "gia_goc", precision = 18)
    private BigDecimal giaGoc;

    @ColumnDefault("0")
    @Column(name = "gia_giam_gia", precision = 18)
    private BigDecimal giaGiamGia;

    @ColumnDefault("0")
    @Column(name = "thanh_tien", precision = 18)
    private BigDecimal thanhTien;

    @ColumnDefault("0")
    @Column(name = "tien_van_chuyen", precision = 18)
    private BigDecimal tienVanChuyen;

    @Nationalized
    @Column(name = "loai_hoa_don", nullable = false, length = 50)
    private String loaiHoaDon;

    @ColumnDefault("sysdatetime()")
    @Column(name = "ngay_mua")
    private Instant ngayMua;

    @Column(name = "ngay_du_kien_nhan")
    private Instant ngayDuKienNhan;

    @Column(name = "ngay_nhan_hang")
    private Instant ngayNhanHang;

    @Nationalized
    @Column(name = "phuong_thuc", length = 50)
    private String phuongThuc;

    @Column(name = "so_tien_thanh_toan", precision = 18)
    private BigDecimal soTienThanhToan;

    @ColumnDefault("0")
    @Column(name = "trang_thai_hoa_don")
    private Integer trangThaiHoaDon;



    @Nationalized
    @Column(name = "nguoi_sua")
    private String nguoiSua;

    @Nationalized
    @Column(name = "nguoi_tao")
    private String nguoiTao;

}