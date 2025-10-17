package com.sd51.fsneakers.features.product.repositories;

import com.sd51.fsneakers.entity.ChatLieu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChatLieuRepository extends JpaRepository<ChatLieu, UUID> {
}
