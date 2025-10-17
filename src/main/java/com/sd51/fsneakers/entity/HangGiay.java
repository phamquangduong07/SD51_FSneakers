package com.sd51.fsneakers.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "hang")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HangGiay extends BaseEntity{
    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    private UUID id;

    @Column(name = "ma", nullable = false, unique = true, length = 255)
    private String ma;

    @Column(name = "ten", length = 255)
    private String ten;

    @Column(name = "trang_thai", nullable = false)
    private Integer trangThai;


}
