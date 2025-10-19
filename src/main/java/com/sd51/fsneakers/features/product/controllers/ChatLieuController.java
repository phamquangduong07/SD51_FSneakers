package com.sd51.fsneakers.features.product.controllers;

import com.sd51.fsneakers.features.product.entity.ChatLieu;
import com.sd51.fsneakers.features.product.services.ChatLieuService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/chat-lieu")
@AllArgsConstructor
public class ChatLieuController {
    private final ChatLieuService chatLieuService;

    @PostMapping("/add")
    public ChatLieu createChatLieu(){
      ChatLieu  chatLieu = new ChatLieu("CL2","Da thường",0);
        return ResponseEntity.ok(chatLieuService.createChatLieu(chatLieu)).getBody();
    }

    @PutMapping("/update/{ma}")
    public  ChatLieu updateChatLieu(@PathVariable("ma") String ma,
                                    @RequestBody ChatLieu chatLieu){
        return ResponseEntity.ok(chatLieuService.updateChatLieuByMa(ma,chatLieu)).getBody();
    }
}
