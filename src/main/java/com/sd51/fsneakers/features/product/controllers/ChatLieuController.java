package com.sd51.fsneakers.features.product.controllers;

import com.sd51.fsneakers.features.product.entity.ChatLieu;
import com.sd51.fsneakers.features.product.services.ChatLieuService;
import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/api/chat-lieu")
@AllArgsConstructor
public class ChatLieuController {
    private final ChatLieuService chatLieuService;

    @GetMapping({"", "/"})
    public List<ChatLieu> getAllChatLieu(){
        return ResponseEntity.ok(chatLieuService.getAllChatLieu()).getBody();
    }

    @PostMapping("/add")
    public ChatLieu createChatLieu(@RequestBody ChatLieu chatLieu){
        return ResponseEntity.ok(chatLieuService.createChatLieu(chatLieu)).getBody();
    }

    @PutMapping("/update/{ma}")
    public  ChatLieu updateChatLieu(@PathVariable("ma") String ma,
                                    @RequestBody ChatLieu chatLieu){
        return ResponseEntity.ok(chatLieuService.updateChatLieuByMa(ma,chatLieu)).getBody();
    }

    @DeleteMapping("/delete/{ma}")
    public ChatLieu deleteChatLieu(@PathVariable("ma") String ma){
        return ResponseEntity.ok(chatLieuService.deleteChatLieuByMa(ma)).getBody();
    }
}
