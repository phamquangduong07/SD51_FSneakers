package com.sd51.fsneakers.features.product.controllers;

import java.util.List;

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

    @GetMapping({ "", "/" })
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

    @PutMapping("/update/{ma}")
    public ResponseEntity<HangGiayResponse> updateHangGiay(@PathVariable String ma, @RequestBody HangGiayRequest hangGiayUpdate) {
        return ResponseEntity.ok(hangGiayService.updateHangGiay(ma, hangGiayUpdate));
    }

    @DeleteMapping("/delete/{ma}")
    public ResponseEntity<Void> deleteHangGiay(@PathVariable String ma) {
        hangGiayService.deleteHangGiay(ma);
        return ResponseEntity.ok().build();
    }

}
