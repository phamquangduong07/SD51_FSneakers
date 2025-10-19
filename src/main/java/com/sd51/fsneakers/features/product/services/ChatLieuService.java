package com.sd51.fsneakers.features.product.services;

import java.util.List;

import com.sd51.fsneakers.features.product.entity.ChatLieu;

public interface ChatLieuService {
    ChatLieu createChatLieu(ChatLieu chatLieu);

    ChatLieu findByMa(String ma);

    ChatLieu updateChatLieuByMa(String ma, ChatLieu chatLieuUpdate);

    ChatLieu deleteChatLieuByMa(String ma);

    List<ChatLieu> getAllChatLieu();

}
