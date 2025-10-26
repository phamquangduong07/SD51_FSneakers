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
public class NguoiDung {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Mã không được để trống")
    @Size(max = 50, message = "Mã tối đa 50 ký tự")
    private String ma;

    @Column(nullable = false)
    @NotBlank(message = "Tên không được để trống")
    @Size(max = 100, message = "Tên tối đa 100 ký tự")
    private String ten;

    @Column(unique = true)
    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    @Size(max = 100, message = "Email tối đa 100 ký tự")
    private String email;

    @NotBlank(message = "Mật khẩu không được để trống")
    @Size(min = 6, message = "Mật khẩu tối thiểu 6 ký tự")
    private String matKhau;

    @Column(unique = true)
    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^\\d{9,11}$", message = "Số điện thoại phải là 9-11 chữ số")
    private String soDienThoai;

    private Boolean gioiTinh;

    private Date ngaySinh;

    private String anh;

    @Column(nullable = false)
    @NotBlank(message = "Vai trò không được để trống")
    private String vaiTro;

    private Integer trangThai;

    private Date ngayTao;

    private Date ngaySua;

    private String nguoiTao;

    private String nguoiSua;
}
