package com.sd51.fsneakers.features.product.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sd51.fsneakers.features.product.dto.request.ChatLieuRequest;
import com.sd51.fsneakers.features.product.dto.response.ChatLieuResponse;
import com.sd51.fsneakers.features.product.entity.ChatLieu;

public interface ChatLieuService {

    // Lấy danh sách tất cả chất liệu
    List<ChatLieuResponse> getAllChatLieu();

    // Lấy danh sách tất cả chất liệu - và phân trang
    Page<ChatLieuResponse> getAllChatLieuPage(Pageable pageable);

    // tìm kiếm chất liệu theo keyword - và phân trang
    Page<ChatLieuResponse> searchChatLieu(String keyword, Integer trangThai, Pageable pageable);

    // tìm kiếm chất liệu theo mã
    ChatLieu findByMa(String ma);

    // tìm kiếm theo id
    ChatLieu findById(UUID id);

    // create chất liệu
    ChatLieuResponse createChatLieu(ChatLieuRequest request);

    // update chất liệu
    ChatLieuResponse updateChatLieuByMa(UUID ma, ChatLieuRequest chatLieuUpdate);

    // delete chất liệu
    ChatLieuResponse deleteChatLieuByMa(UUID ma);

}
