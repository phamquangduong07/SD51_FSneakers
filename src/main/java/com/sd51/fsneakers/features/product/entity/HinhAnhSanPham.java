package com.sd51.fsneakers.features.product.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sd51.fsneakers.commons.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" }) // Bỏ qua field kỹ thuật của Hibernate khi lazy load
@Entity
@Table(name = "hinh_anh")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HinhAnhSanPham extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uniqueidentifier")
    UUID id;

    @Column(nullable = false, unique = true, length = 255)
    String ma;

    @Column(length = 255)
    String ten;

    @Column(length = 500)
    String url;

    @Column(name = "trang_thai", nullable = false)
    Integer trangThai;

    /**
     * 🖼️ Entity: Hình ảnh sản phẩm
     * - Một hình ảnh thuộc về 1 sản phẩm chi tiết.
     * - Dùng @JsonBackReference để không serialize ngược lên sản phẩm, tránh vòng
     * lặp JSON.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chi_tiet_san_pham_id", nullable = false)
    @JsonBackReference // Không serialize ngược lại (tránh vòng lặp JSON)
    SanPhamChiTiet chiTietSanPham;
}
