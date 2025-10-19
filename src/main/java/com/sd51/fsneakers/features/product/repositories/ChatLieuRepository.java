package com.sd51.fsneakers.features.product.repositories;

import com.sd51.fsneakers.features.product.entity.ChatLieu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ChatLieuRepository extends JpaRepository<ChatLieu, UUID> {
    ChatLieu findByMa(String ma);
}
