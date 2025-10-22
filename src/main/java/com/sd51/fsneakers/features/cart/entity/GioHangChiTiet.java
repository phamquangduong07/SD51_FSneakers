package com.sd51.fsneakers.features.cart.entity;

import com.sd51.fsneakers.features.product.entity.SanPhamChiTiet;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "gio_hang_chi_tiet")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GioHangChiTiet {

    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    private UUID id;

    @Column(name = "so_luong", nullable = false)
    private Integer soLuong;

    @Column(name = "gia_giam_gia", precision = 18, scale = 0)
    private BigDecimal giaGiamGia = BigDecimal.ZERO;

    @Column(name = "trang_thai", nullable = false)
    private Integer trangThai;

    // Mỗi chi tiết giỏ hàng thuộc về 1 giỏ hàng
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gio_hang_id", nullable = false)
    private GioHang gioHang;

    // Mỗi chi tiết giỏ hàng gắn với 1 sản phẩm cụ thể (ChiTietSanPham)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chi_tiet_sp_id", nullable = false)
    private SanPhamChiTiet sanPhamChiTiet;
}
