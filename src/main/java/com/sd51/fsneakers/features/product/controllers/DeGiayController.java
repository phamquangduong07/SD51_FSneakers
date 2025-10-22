package com.sd51.fsneakers.features.product.controllers;

import com.sd51.fsneakers.features.product.dto.request.DeGiayRequest;
import com.sd51.fsneakers.features.product.dto.response.DeGiayResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import com.sd51.fsneakers.features.product.services.DeGiayService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("api/v1/de-giay")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DeGiayController {

    DeGiayService deGiayService;

    @GetMapping({ "", "/" })
    public ResponseEntity<List<DeGiayResponse>> getAllDeGiay() {
        return ResponseEntity.ok(deGiayService.getAllDeGiay());
    }

    @GetMapping("/page")
    public ResponseEntity<Page<DeGiayResponse>> getAllDeGiayPage(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(deGiayService.getAllDeGiayPage(pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<DeGiayResponse>> searchDeGiay(@RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer trangThai,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(deGiayService.searchDeGiay(keyword, trangThai, pageable));
    }

    @PostMapping("/add")
    public ResponseEntity<DeGiayResponse> addDeGiay(@RequestBody DeGiayRequest deGiay) {
        return ResponseEntity.ok(deGiayService.createDeGiay(deGiay));
    }

    @PutMapping("/update/{ma}")
    public ResponseEntity<DeGiayResponse> updateDeGiay(@PathVariable("ma") String ma, @RequestBody DeGiayRequest deGiay) {
        return ResponseEntity.ok(deGiayService.updateDeGiay(ma, deGiay));
    }

    @DeleteMapping("/delete/{ma}")
    public ResponseEntity<Void> deleteDeGiay(@PathVariable("ma") String ma) {
        deGiayService.deleteDeGiay(ma);
        return ResponseEntity.ok().build();
    }

}
