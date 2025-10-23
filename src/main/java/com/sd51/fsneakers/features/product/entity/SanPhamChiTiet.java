package com.sd51.fsneakers.features.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sd51.fsneakers.commons.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" }) // Bỏ qua field kỹ thuật của Hibernate khi lazy load
@Entity
@Table(name = "chi_tiet_san_pham")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SanPhamChiTiet extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uniqueidentifier")
    UUID id;

    @Column(nullable = false, unique = true, length = 255)
    String ma;

    @Column(name = "ma_qr", unique = true, length = 255)
    String maQr;

    @Column(name = "gia_ban", nullable = false, precision = 18, scale = 0)
    BigDecimal giaBan;

    @Column(name = "gia_nhap", precision = 18, scale = 0)
    BigDecimal giaNhap;

    @Column(name = "so_luong", nullable = false)
    Integer soLuong;

    @Column(name = "mo_ta", columnDefinition = "NVARCHAR(MAX)")
    String moTa;

    @Column(name = "trang_thai", nullable = false)
    Integer trangThai;

    @Column(name = "nguoi_tao")
    String nguoiTao;

    @Column(name = "nguoi_sua")
    String nguoiSua;

    // 🔗 Quan hệ tới các bảng khác
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_lieu_id", nullable = false)
    ChatLieu chatLieu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "danh_muc_id", nullable = false)
    DanhMuc danhMuc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "de_giay_id", nullable = false)
    DeGiay deGiay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hang_id", nullable = false)
    HangGiay hangGiay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kich_thuoc_id", nullable = false)
    KichThuoc kichThuoc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mau_sac_id", nullable = false)
    MauSac mauSac;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "san_pham_id", nullable = false)
    SanPham sanPham;

    /**
     * - Một sản phẩm chi tiết có thể có nhiều hình ảnh.
     * - Dùng @JsonManagedReference để Jackson chỉ serialize 1 chiều (từ cha ->
     * con),
     * tránh vòng lặp vô hạn khi chuyển sang JSON.
     */
    @OneToMany(mappedBy = "chiTietSanPham", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference // Cho phép Jackson serialize ra JSON (bên cha)
    List<HinhAnhSanPham> hinhAnhSanPhams;

}
