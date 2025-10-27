package com.sd51.fsneakers.features.product.services.impl;

import com.sd51.fsneakers.features.product.mapper.ChatLieuMapper;
import com.sd51.fsneakers.features.product.dto.request.ChatLieuRequest;
import com.sd51.fsneakers.features.product.dto.response.ChatLieuResponse;
import com.sd51.fsneakers.features.product.entity.ChatLieu;
import com.sd51.fsneakers.features.product.repositories.ChatLieuRepository;
import com.sd51.fsneakers.features.product.services.ChatLieuService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChatLieuServiceImpl implements ChatLieuService {

    ChatLieuRepository chatLieuRepository;

    ChatLieuMapper chatLieuMapper;

    @Override
    public List<ChatLieuResponse> getAllChatLieu() {
        return chatLieuRepository.findAll().stream()
                .map(chatLieuMapper::toResponse)
                .toList();
    }

    @Override
    public Page<ChatLieuResponse> getAllChatLieuPage(Pageable pageable) {
        return chatLieuRepository.getAllPage(pageable).map(chatLieuMapper::toResponse);
    }

    @Override
    public Page<ChatLieuResponse> searchChatLieu(String keyword, Integer trangThai, Pageable pageable) {
        return chatLieuRepository.searchChatLieu(keyword, trangThai, pageable).map(chatLieuMapper::toResponse);
    }

    @Override
    public ChatLieu findByMa(String ma) {
        return chatLieuRepository.findByMa(ma);
    }

    @Override
    public ChatLieu findById(UUID id) {
        return chatLieuRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy dữ liệu với id = " + id));
    }


    @Override
    public ChatLieuResponse createChatLieu(ChatLieuRequest request) {
        if (findByMa(request.getMa()) != null) {
            throw new RuntimeException("Mã chất liệu '" + request.getMa() + "' đã tồn tại!");
        }

        ChatLieu chatLieu = chatLieuMapper.toEntity(request);
        chatLieuRepository.save(chatLieu);
        return chatLieuMapper.toResponse(chatLieu);
    }


    @Override
    public ChatLieuResponse updateChatLieuByMa(UUID ma, ChatLieuRequest chatLieuUpdate) {
        ChatLieu existing = findById(ma);
        if (existing == null) {
            throw new RuntimeException("Mã chất liệu '" + ma + "' không tồn tại!");
        }
        if (!existing.getMa().equals(chatLieuUpdate.getMa())) {
            if (findByMa(chatLieuUpdate.getMa()) != null) {
                throw new RuntimeException("Mã chất liệu '" + chatLieuUpdate.getMa() + "' đã tồn tại khác !");
            } else {

            }
        }
        // Cập nhật các field (tự động với MapStruct)
        chatLieuMapper.toUpdate(existing, chatLieuUpdate);
        ChatLieu update = chatLieuRepository.save(existing);
        return chatLieuMapper.toResponse(update);
    }

    @Override
    public ChatLieuResponse deleteChatLieuByMa(UUID ma) {
        ChatLieu existing = findById(ma);
        if (existing == null) {
            throw new RuntimeException("Mã chất liệu '" + ma + "' không tồn tại!");
        }
        ChatLieuResponse deleteResponse = chatLieuMapper.toResponse(existing);
        chatLieuRepository.delete(existing);
        return deleteResponse;
    }


}
