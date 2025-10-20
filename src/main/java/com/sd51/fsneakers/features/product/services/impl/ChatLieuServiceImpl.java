package com.sd51.fsneakers.features.product.services.impl;

import com.sd51.fsneakers.features.product.entity.ChatLieu;
import com.sd51.fsneakers.features.product.repositories.ChatLieuRepository;
import com.sd51.fsneakers.features.product.services.ChatLieuService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChatLieuServiceImpl implements ChatLieuService {

    ChatLieuRepository chatLieuRepository;

    @Override
    public ChatLieu createChatLieu(ChatLieu chatLieu) {
        if (findByMa(chatLieu.getMa()) != null) {
            throw new RuntimeException("Mã chất liệu '" + chatLieu.getMa() + "' đã tồn tại!");
        }

        return chatLieuRepository.save(chatLieu);
    }

    @Override
    public ChatLieu findByMa(String ma) {
        return chatLieuRepository.findByMa(ma);
    }

    @Override
    public ChatLieu updateChatLieuByMa(String ma, ChatLieu chatLieuUpdate) {
        ChatLieu existing = findByMa(ma);
        if (existing == null) {
            throw new RuntimeException("Mã chất liệu '" + ma + "' không tồn tại!");
        }
        if (!chatLieuUpdate.getMa().equals(ma)) {
            if (findByMa(chatLieuUpdate.getMa()) != null) {
                throw new RuntimeException("Mã chất liệu '" + chatLieuUpdate.getMa() + "' đã tồn tại khác !");
            } else {

            }
        }
        // Cập nhật các thuộc tính của existing với giá trị từ chatLieuUpdate
        existing.setMa(chatLieuUpdate.getMa());
        existing.setTen(chatLieuUpdate.getTen());
        existing.setTrangThai(chatLieuUpdate.getTrangThai());
        return chatLieuRepository.save(existing);
    }

    @Override
    public ChatLieu deleteChatLieuByMa(String ma) {
        ChatLieu existing = findByMa(ma);
        if (existing == null) {
            throw new RuntimeException("Mã chất liệu '" + ma + "' không tồn tại!");
        }
        chatLieuRepository.delete(existing);
        return existing;
    }

    @Override
    public List<ChatLieu> getAllChatLieu() {
        return chatLieuRepository.findAll();
    }

}
