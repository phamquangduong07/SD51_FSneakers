package com.sd51.fsneakers.features.product.entity;

import com.sd51.fsneakers.commons.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "chat_lieu")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatLieu extends BaseEntity {
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

    public ChatLieu( String ma, String ten, Integer trangThai) {

        this.ma = ma;
        this.ten = ten;
        this.trangThai = trangThai;
    }
}
