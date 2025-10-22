package com.sd51.fsneakers.features.product.controllers;

import com.sd51.fsneakers.features.product.dto.request.ChatLieuRequest;
import com.sd51.fsneakers.features.product.dto.response.ChatLieuResponse;
import com.sd51.fsneakers.features.product.services.ChatLieuService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/chat-lieu")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class ChatLieuController {
    ChatLieuService chatLieuService;

    @GetMapping({ "", "/" })
    public ResponseEntity<List<ChatLieuResponse>> getAllChatLieu() {
        return ResponseEntity.ok(chatLieuService.getAllChatLieu());
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ChatLieuResponse>> getAllChatLieuPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(chatLieuService.getAllChatLieuPage(pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ChatLieuResponse>> searchChatLieu(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer trangThai,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(chatLieuService.searchChatLieu(keyword, trangThai, pageable));
    }

    @PostMapping("/add")
    public ResponseEntity<ChatLieuResponse> createChatLieu(@RequestBody ChatLieuRequest request) {
        return ResponseEntity.ok(chatLieuService.createChatLieu(request));
    }

    @PutMapping("/update/{ma}")
    public ResponseEntity<ChatLieuResponse> updateChatLieu(@PathVariable("ma") String ma,
            @RequestBody ChatLieuRequest request) {
        ChatLieuResponse update = chatLieuService.updateChatLieuByMa(ma, request);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/delete/{ma}")
    public ResponseEntity<ChatLieuResponse> deleteChatLieu(@PathVariable("ma") String ma) {

        return ResponseEntity.ok(chatLieuService.deleteChatLieuByMa(ma));
    }
}
