package com.sd51.fsneakers.entity;
import jakarta.persistence.*;
import lombok.*;


import java.util.UUID;
@Entity
@Table(name = "nguoidung_voucher")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NguoiDungVoucher extends BaseEntity{
    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "khach_hang_id", nullable = false)
    private KhachHang khachHang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voucher_id", nullable = false)
    private Voucher voucher;

    @Column(name = "trang_thai", nullable = false)
    private Integer trangThai;
}
