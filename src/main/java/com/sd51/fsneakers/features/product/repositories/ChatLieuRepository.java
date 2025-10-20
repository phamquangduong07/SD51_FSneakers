package com.sd51.fsneakers.features.product.repositories;

import com.sd51.fsneakers.features.product.entity.ChatLieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChatLieuRepository extends JpaRepository<ChatLieu, UUID> {
    ChatLieu findByMa(String ma);
}
