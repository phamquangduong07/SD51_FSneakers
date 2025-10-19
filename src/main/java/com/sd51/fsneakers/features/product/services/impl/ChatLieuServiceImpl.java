package com.sd51.fsneakers.features.product.services.impl;

import com.sd51.fsneakers.features.product.entity.ChatLieu;
import com.sd51.fsneakers.features.product.repositories.ChatLieuRepository;
import com.sd51.fsneakers.features.product.services.ChatLieuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChatLieuServiceImpl implements ChatLieuService {
    private final ChatLieuRepository chatLieuRepository;

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
    public ChatLieu updateChatLieuByMa(String ma, ChatLieu chatLieuNew) {
        ChatLieu existing = findByMa(ma);
        if(existing== null){
            throw new RuntimeException("Mã chất liệu '" + ma + "' không tồn tại!");
        }
        if(!chatLieuNew.getMa().equals(ma)){
            if(findByMa(chatLieuNew.getMa())!=null){
                throw new RuntimeException("Mã chất liệu '" + chatLieuNew.getMa() + "' đã tồn tại khác !");
            }else {

            }
        }
        existing.setMa(chatLieuNew.getMa());
        existing.setTen(chatLieuNew.getTen());
        existing.setTrangThai(chatLieuNew.getTrangThai());
        return chatLieuRepository.save(existing);
    }


}
