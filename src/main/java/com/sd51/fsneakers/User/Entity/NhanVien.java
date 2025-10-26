package com.sd51.fsneakers.User.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "nhan_vien")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NhanVien {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "nguoi_dung_id", nullable = false)
    @NotNull(message = "Người dùng (nguoiDung) là bắt buộc")
    private NguoiDung NguoiDung;

    @NotBlank(message = "CCCD/CMND không được để trống")
    @Pattern(regexp = "^\\d{9,12}$", message = "CCCD/CMND phải là 9-12 chữ số")
    private String cccd;

    @NotBlank(message = "Chức vụ không được để trống")
    private String chucVu;
}
