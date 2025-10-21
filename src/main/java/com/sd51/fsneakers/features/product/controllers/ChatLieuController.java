package com.sd51.fsneakers.features.product.controllers;

import com.sd51.fsneakers.features.product.entity.ChatLieu;
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
    public List<ChatLieu> getAllChatLieu() {
        return ResponseEntity.ok(chatLieuService.getAllChatLieu()).getBody();
    }

    @GetMapping("/page")
    public Page<ChatLieu> getAllChatLieuPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(chatLieuService.getAllChatLieuPage(pageable)).getBody();
    }

    @GetMapping("/search")
    public Page<ChatLieu> searchChatLieu(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer trangThai,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity
                .ok(chatLieuService.searchChatLieu(keyword, trangThai, pageable))
                .getBody();
    }

    @PostMapping("/add")
    public ChatLieu createChatLieu(@RequestBody ChatLieu chatLieu) {
        return ResponseEntity.ok(chatLieuService.createChatLieu(chatLieu)).getBody();
    }

    @PutMapping("/update/{ma}")
    public ChatLieu updateChatLieu(@PathVariable("ma") String ma,
            @RequestBody ChatLieu chatLieu) {
        return ResponseEntity.ok(chatLieuService.updateChatLieuByMa(ma, chatLieu)).getBody();
    }

    @DeleteMapping("/delete/{ma}")
    public ChatLieu deleteChatLieu(@PathVariable("ma") String ma) {
        return ResponseEntity.ok(chatLieuService.deleteChatLieuByMa(ma)).getBody();
    }
}
