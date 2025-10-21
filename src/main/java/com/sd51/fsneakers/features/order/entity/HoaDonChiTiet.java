package com.sd51.fsneakers.features.order.entity;

import com.sd51.fsneakers.features.product.entity.SanPhamChiTiet;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "hoa_don_chi_tiet")
@AllArgsConstructor
@NoArgsConstructor
public class HoaDonChiTiet {
    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hoa_don_id", nullable = false)
    private HoaDon hoaDon;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "chi_tiet_san_pham_id", nullable = false)
    private SanPhamChiTiet chiTietSanPham;

    @Column(name = "so_luong", nullable = false)
    private Integer soLuong;

    @ColumnDefault("0")
    @Column(name = "don_gia", precision = 18)
    private BigDecimal donGia;

    @Column(name = "trang_thai", nullable = false)
    private Integer trangThai;

}