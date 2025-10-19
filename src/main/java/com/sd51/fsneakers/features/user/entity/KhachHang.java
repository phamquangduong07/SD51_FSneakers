package com.sd51.fsneakers.features.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "khach_hang")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KhachHang {
    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    private UUID id;

    @OneToOne
    @JoinColumn(name = "nguoi_dung_id", nullable = false, unique = true)
    private NguoiDung nguoiDung;

    @Column(name = "diem", columnDefinition = "int default 0")
    private Integer diem = 0;

    @Column(name = "hang_khach_hang", length = 100)
    private String hangKhachHang;

    @Column(name = "ngay_tham_gia")
    private LocalDateTime ngayThamGia;
}
