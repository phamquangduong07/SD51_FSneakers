package com.sd51.fsneakers.features.product.controllers;

import java.util.List;
import java.util.UUID;

import com.sd51.fsneakers.features.product.dto.request.HangGiayRequest;
import com.sd51.fsneakers.features.product.dto.response.HangGiayResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping({"", "/"})
    public ResponseEntity<List<HangGiayResponse>> getAllHangGiay() {
        return ResponseEntity.ok(hangGiayService.getAllHangGiay());
    }

    @GetMapping("/page")
    public ResponseEntity<Page<HangGiayResponse>> getAllHangGiayPage(@RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(hangGiayService.getAllHangGiayPage(pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<HangGiayResponse>> searchHangGiay(@RequestParam(required = false) String keyword,
                                                                 @RequestParam(required = false) Integer trangThai,
                                                                 @RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(hangGiayService.searchHangGiay(keyword, trangThai, pageable));
    }

    @PostMapping("/add")
    public ResponseEntity<HangGiayResponse> createHangGiay(@RequestBody HangGiayRequest hangGiay) {
        return ResponseEntity.ok(hangGiayService.createHangGiay(hangGiay));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HangGiayResponse> updateHangGiay(@PathVariable UUID id, @RequestBody HangGiayRequest hangGiayUpdate) {
        return ResponseEntity.ok(hangGiayService.updateHangGiay(id, hangGiayUpdate));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HangGiayResponse> deleteHangGiay(@PathVariable UUID id) {
        return ResponseEntity.ok(hangGiayService.deleteHangGiay(id));
    }

}
