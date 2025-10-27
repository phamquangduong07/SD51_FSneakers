package com.sd51.fsneakers.features.order.entity;

import com.sd51.fsneakers.commons.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "thanh_toan")
@AllArgsConstructor
@NoArgsConstructor
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
}
