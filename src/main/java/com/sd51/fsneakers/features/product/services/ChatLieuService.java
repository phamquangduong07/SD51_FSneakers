package com.sd51.fsneakers.features.product.services;

import com.sd51.fsneakers.features.product.entity.ChatLieu;

public interface ChatLieuService {
    ChatLieu createChatLieu(ChatLieu chatLieu);
    ChatLieu findByMa(String ma);
    ChatLieu updateChatLieuByMa(String ma, ChatLieu chatLieuNew);

}
