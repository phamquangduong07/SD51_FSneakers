package com.sd51.fsneakers.features.product.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/page")
    public Page<HangGiay> getAllHangGiayPage(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(hangGiayService.getAllHangGiayPage(pageable)).getBody();
    }

    @GetMapping("/search")
    public Page<HangGiay> searchHangGiay(@RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer trangThai,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return hangGiayService.searchHangGiay(keyword, trangThai, pageable);
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
