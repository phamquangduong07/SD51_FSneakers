package com.sd51.fsneakers.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "nguoi_dung")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NguoiDung extends BaseEntity{
    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    private UUID id;

    @Column(name = "ma", nullable = false, unique = true, length = 255)
    private String ma;

    @Column(name = "ten", nullable = false, length = 255)
    private String ten;

    @Column(name = "email", unique = true, length = 255)
    private String email;

    @Column(name = "mat_khau", length = 255)
    private String matKhau;

    @Column(name = "so_dien_thoai", unique = true, length = 50)
    private String soDienThoai;

    @Column(name = "gioi_tinh")
    private Boolean gioiTinh;

    @Column(name = "ngay_sinh")
    private java.time.LocalDate ngaySinh;

    @Column(name = "anh", length = 255)
    private String anh;

    @Column(name = "vai_tro", length = 100)
    private String vaiTro;

    @Column(name = "trang_thai", nullable = false)
    private Integer trangThai = 1;



    @Column(name = "nguoi_tao")
    private String nguoiTao;

    @Column(name = "nguoi_sua")
    private String nguoiSua;

    // Quan hệ 1-1 với KhachHang
    @OneToOne(mappedBy = "nguoiDung", cascade = CascadeType.ALL)
    private KhachHang khachHang;

    // Quan hệ 1-1 với NhanVien
    @OneToOne(mappedBy = "nguoiDung", cascade = CascadeType.ALL)
    private NhanVien nhanVien;
}
