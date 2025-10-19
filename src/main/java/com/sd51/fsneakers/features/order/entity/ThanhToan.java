package com.sd51.fsneakers.features.order.entity;

import com.sd51.fsneakers.commons.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "thanh_toan")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ThanhToan extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    private UUID id;

    // Mỗi thanh toán thuộc về một hóa đơn
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hoa_don_id", nullable = false)
    private HoaDon hoaDon;

    @Column(name = "tong_tien", precision = 38, scale = 2)
    private BigDecimal tongTien;

    @Column(name = "tien_mat", precision = 38, scale = 2)
    private BigDecimal tienMat;

    @Column(name = "chuyen_khoan", precision = 38, scale = 2)
    private BigDecimal chuyenKhoan;

    // 0 = tiền mặt, 1 = chuyển khoản, 2 = VNPay, 3 = kết hợp,...
    @Column(name = "phuong_thuc", nullable = false)
    private Integer phuongThuc;

    @Column(name = "phuong_thuc_vnp", length = 255)
    private String phuongThucVnp; // Mã giao dịch VNPay nếu có

    // 0 = chờ xử lý, 1 = thành công, 2 = thất bại, 3 = hoàn tiền
    @Column(name = "trang_thai", nullable = false)
    private Integer trangThai;


}
