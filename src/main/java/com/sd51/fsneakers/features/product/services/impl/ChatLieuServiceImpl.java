package com.sd51.fsneakers.features.product.services.impl;

import com.sd51.fsneakers.entity.ChatLieu;
import com.sd51.fsneakers.features.product.repositories.ChatLieuRepository;
import com.sd51.fsneakers.features.product.services.ChatLieuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ChatLieuServiceImpl implements ChatLieuService {
    private final ChatLieuRepository chatLieuRepository;

    @Override
    public ChatLieu createChatLieu(ChatLieu chatLieu) {

        return chatLieuRepository.save(chatLieu);
    }


}
