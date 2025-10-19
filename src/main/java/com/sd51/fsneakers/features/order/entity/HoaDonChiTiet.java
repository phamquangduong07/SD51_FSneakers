package com.sd51.fsneakers.features.order.entity;

import com.sd51.fsneakers.features.product.entity.SanPhamChiTiet;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "hoa_don_chi_tiet")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HoaDonChiTiet {
    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    private UUID id;

    // Liên kết tới hóa đơn
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hoa_don_id", nullable = false)
    private HoaDon hoaDon;

    // Liên kết tới sản phẩm chi tiết
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chi_tiet_san_pham_id", nullable = false)
    private SanPhamChiTiet sanPhamChiTiet;

    @Column(name = "so_luong", nullable = false)
    private Integer soLuong;

    @Column(name = "gia_giam", precision = 18, scale = 0)
    private BigDecimal giaGiam = BigDecimal.ZERO;

    @Column(name = "gia_sau_giam", precision = 18, scale = 0)
    private BigDecimal giaSauGiam = BigDecimal.ZERO;

    @Column(name = "trang_thai", nullable = false)
    private Integer trangThai;
}
