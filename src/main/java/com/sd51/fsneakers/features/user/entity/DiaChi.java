package com.sd51.fsneakers.features.user.entity;

import com.sd51.fsneakers.commons.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "dia_chi")
@AllArgsConstructor
@NoArgsConstructor
public class DiaChi extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "ma", nullable = false, unique = true, length = 255)
    private String ma;

    @Column(name = "ten_nguoi_nhan", nullable = false, length = 100)
    private String tenNguoiNhan;

    @Column(name = "so_dien_thoai", nullable = false, length = 20)
    private String soDienThoai;

    @Column(name = "dia_chi_cu_the", length = 255)
    private String diaChiCuThe;

    @Column(name = "ten_thanh_pho", length = 100)
    private String tenThanhPho;

    @Column(name = "ten_huyen", length = 100)
    private String tenHuyen;

    @Column(name = "ten_xa", length = 100)
    private String tenXa;

    @Column(name = "trang_thai", nullable = false)
    private Integer trangThai;

    // Quan hệ nhiều địa chỉ cho một khách hàng
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "khach_hang_id", nullable = false)
    private KhachHang khachHang;
}
