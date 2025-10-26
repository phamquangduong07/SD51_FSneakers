package com.sd51.fsneakers.User.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "nguoi_dung")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "nguoi_dung_id", nullable = false)
    @NotNull(message = "Người dùng (nguoiDung) là bắt buộc")
    private NguoiDung NguoiDung;

    private Integer diem;

    private String hangKhachHang;

    @PastOrPresent(message = "Ngày tham gia không thể ở tương lai")
    private Date ngayThamGia;
}
