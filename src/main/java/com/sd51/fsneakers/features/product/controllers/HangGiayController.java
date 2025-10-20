package com.sd51.fsneakers.features.product.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.sd51.fsneakers.features.product.entity.HangGiay;
import com.sd51.fsneakers.features.product.services.HangGiayService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("api/v1/hang-giay")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HangGiayController {

    HangGiayService hangGiayService;

    @GetMapping({ "", "/" })
    public List<HangGiay> getAllHangGiay() {
        return ResponseEntity.ok(hangGiayService.getAllHangGiay()).getBody();
    }

    @PostMapping("/add")
    public HangGiay createHangGiay(@RequestBody HangGiay hangGiay) {
        return ResponseEntity.ok(hangGiayService.createHangGiay(hangGiay)).getBody();
    }

    @PutMapping("/update/{ma}")
    public HangGiay updateHangGiay(@PathVariable String ma, @RequestBody HangGiay hangGiayUpdate) {
        return ResponseEntity.ok(hangGiayService.updateHangGiay(ma, hangGiayUpdate)).getBody();
    }

    @DeleteMapping("/delete/{ma}")
    public HangGiay deleteHangGiay(@PathVariable String ma) {
        return ResponseEntity.ok(hangGiayService.deleteHangGiay(ma)).getBody();
    }
}
