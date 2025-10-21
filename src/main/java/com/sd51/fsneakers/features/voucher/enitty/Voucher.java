package com.sd51.fsneakers.features.voucher.enitty;

import com.sd51.fsneakers.commons.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "voucher")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Voucher extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    private UUID id;

    @Column(name = "ma", nullable = false, unique = true, length = 255)
    private String ma;

    @Column(name = "ten", length = 255)
    private String ten;

    // Điều kiện áp dụng (VD: đơn hàng >= 500000)
    @Column(name = "dieu_kien", precision = 18, scale = 0)
    private BigDecimal dieuKien;

    // Giảm tối đa (VD: tối đa 100k)
    @Column(name = "giam_toi_da", precision = 18, scale = 0)
    private BigDecimal giamToiDa;

    // Giảm tối thiểu (nếu có quy định)
    @Column(name = "giam_toi_thieu", precision = 18, scale = 0)
    private BigDecimal giamToiThieu;

    @Column(name = "so_luong", nullable = false)
    private Integer soLuong;

    @Column(name = "ngay_bat_dau")
    private LocalDateTime ngayBatDau;

    @Column(name = "ngay_ket_thuc")
    private LocalDateTime ngayKetThuc;

    @Column(name = "loai_voucher", length = 255)
    private String loaiVoucher;

    @Column(name = "nguoi_tao")
    private String nguoiTao;

    @Column(name = "nguoi_sua")
    private String nguoiSua;

    @Column(name = "trang_thai", nullable = false)
    private Integer trangThai;
}