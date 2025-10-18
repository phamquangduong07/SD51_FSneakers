package com.sd51.fsneaker.Entity;


import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "san_pham")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SanPham extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "ma", unique = true, nullable = false, length = 255)
    private String ma;

    @Column(name = "ten", length = 255, nullable = false)
    private String ten;

    @Column(name = "mo_ta", length = 500, nullable = true)
    private String moTa;


    @Column(name = "trang_thai", nullable = false)
    private int trangThai;

}
