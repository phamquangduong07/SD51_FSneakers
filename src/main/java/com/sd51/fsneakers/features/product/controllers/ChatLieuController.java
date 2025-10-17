package com.sd51.fsneakers.features.product.controllers;

import com.sd51.fsneakers.entity.ChatLieu;
import com.sd51.fsneakers.features.product.services.ChatLieuService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/chat-lieu")
@AllArgsConstructor
public class ChatLieuController {
    private final ChatLieuService chatLieuService;

    @PostMapping("/add")
    public ChatLieu createChatLieu(){
      ChatLieu  chatLieu = new ChatLieu("CL1","Da thường",0);
        return ResponseEntity.ok(chatLieuService.createChatLieu(chatLieu)).getBody();
    }
}
