package com.sd51.fsneakers.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "lich_su_hoa_don")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LichSuHoaDon extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    private UUID id;

    // Liên kết tới hóa đơn
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hoa_don_id", nullable = false)
    private HoaDon hoaDon;

    @Column(name = "ghi_chu", length = 255)
    private String ghiChu;

    // 0 = tạo mới, 1 = xác nhận, 2 = đang giao, 3 = hoàn thành, 4 = hủy, ...
    @Column(name = "trang_thai", nullable = false)
    private Integer trangThai;


}