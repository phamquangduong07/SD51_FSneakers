package com.sd51.fsneakers.features.order.entity;

import com.sd51.fsneakers.commons.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
import java.util.prefs.BackingStoreException;

@Getter
@Setter
@Entity
@Table(name = "thanh_toan")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ThanhToan extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    private UUID id;

    @Column(name = "tong_tien", precision = 38, scale = 2)
    private BigDecimal tongTien;

    @Column(name = "tien_mat", precision = 38, scale = 2)
    private BigDecimal tienMat;

    @Column(name = "chuyen_khoan", precision = 38, scale = 2)
    private BigDecimal chuyenKhoan;

    @Column(name = "phuong_thuc", nullable = false)
    private Integer phuongThuc;

    @Column(name = "phuong_thuc_vnp")
    private String phuongThucVnp;

    @Column(name = "trang_thai", nullable = false)
    private Integer trangThai;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hoa_don_id", nullable = false)
    private HoaDon hoaDon;

}