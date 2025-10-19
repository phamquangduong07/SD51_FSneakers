package com.sd51.fsneakers.features.cart.entity;

import com.sd51.fsneakers.commons.BaseEntity;
import com.sd51.fsneakers.features.user.entity.KhachHang;
import com.sd51.fsneakers.features.user.entity.NhanVien;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table(name = "gio_hang")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GioHang extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    private UUID id;

    @Column(name = "ma", nullable = false, unique = true, length = 255)
    private String ma;



    @Column(name = "trang_thai", nullable = false)
    private Integer trangThai;

    // Mỗi giỏ hàng thuộc về 1 khách hàng
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "khach_hang_id", nullable = false)
    private KhachHang khachHang;



    // Có thể do 1 nhân viên tạo (POS)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nhan_vien_id")
    private NhanVien nhanVien;
    // Liên kết 1-nhiều với bảng chi tiết giỏ hàng
    @OneToMany(mappedBy = "gioHang", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GioHangChiTiet> chiTietGioHang = new ArrayList<>();
}