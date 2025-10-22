package com.sd51.fsneakers.features.mapper;


import com.sd51.fsneakers.features.product.dto.request.ChatLieuRequest;
import com.sd51.fsneakers.features.product.dto.response.ChatLieuResponse;
import com.sd51.fsneakers.features.product.entity.ChatLieu;

public class ChatLieuMapper {

    public static ChatLieu toEntity(ChatLieuRequest req) {
        if (req == null) return null;

        ChatLieu entity = new ChatLieu();
        entity.setMa(req.getMa());
        entity.setTen(req.getTen());
        entity.setTrangThai(req.getTrangThai());

        return entity;
    }

    public static ChatLieuResponse toResponse(ChatLieu chatLieu) {
        if (chatLieu == null) return null;

        ChatLieuResponse res = new ChatLieuResponse();
        res.setId(chatLieu.getId());
        res.setMa(chatLieu.getMa());
        res.setTen(chatLieu.getTen());
        res.setTrangThai(chatLieu.getTrangThai());
        res.setNgayTao(chatLieu.getNgayTao());
        res.setNgaySua(chatLieu.getNgaySua());

        return res;
    }

    public static void toUpdate(ChatLieu chatLieu, ChatLieuRequest req) {
        if (chatLieu == null || req == null) return;

        if (chatLieu.getMa() == null) chatLieu.setMa(req.getMa());
        if (req.getTen() != null) chatLieu.setTen(req.getTen());
        if (req.getTrangThai() != null) chatLieu.setTrangThai(req.getTrangThai());
    }

}
