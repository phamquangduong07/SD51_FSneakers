package com.sd51.fsneakers.features.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sd51.fsneakers.commons.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = "chat_lieu")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatLieu extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    UUID id;

    @Column(name = "ma", nullable = false, unique = true, length = 255)
    String ma;

    @Column(name = "ten", length = 255)
    String ten;

    @Column(name = "trang_thai", nullable = false)
    Integer trangThai;

    public ChatLieu(String ma, String ten, Integer trangThai) {

        this.ma = ma;
        this.ten = ten;
        this.trangThai = trangThai;
    }

    public ChatLieu(UUID id) {
        this.id =id;
    }
}
