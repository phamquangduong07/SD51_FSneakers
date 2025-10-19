package com.sd51.fsneakers.features.product.entity;
import com.sd51.fsneakers.commons.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;
@Entity
@Table(name = "hinh_anh")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HinhAnhSanPham extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uniqueidentifier")
    private UUID id;

    @Column(nullable = false, unique = true, length = 255)
    private String ma;

    @Column(length = 255)
    private String ten;

    @Column(length = 500)
    private String url;

    @Column(name = "trang_thai", nullable = false)
    private Integer trangThai;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chi_tiet_san_pham_id", nullable = false)
    private SanPhamChiTiet chiTietSanPham;
}
