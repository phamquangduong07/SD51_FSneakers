package com.sd51.fsneakers.features.product.controllers;

import java.util.List;

import com.sd51.fsneakers.features.product.dto.request.MauSacRequest;
import com.sd51.fsneakers.features.product.dto.response.MauSacResponse;
import com.sd51.fsneakers.features.product.entity.MauSac;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sd51.fsneakers.features.product.services.MauSacService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("api/v1/mau-sac")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MauSacController {

    MauSacService mauSacService;

    @GetMapping({ "", "/" })
    public ResponseEntity<List<MauSacResponse>> getAllMauSac() {
        return ResponseEntity.ok(mauSacService.getAllMauSac());
    }

    @GetMapping("/page")
    public ResponseEntity<Page<MauSacResponse>> getAllMauSacPage(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(mauSacService.getAllMauSacPage(pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<MauSacResponse>> searchMauSac(@RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer trangThai,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(mauSacService.searchMauSac(keyword, trangThai, pageable));
    }

    @PostMapping("/add")
    public ResponseEntity<MauSacResponse> createMauSac(@RequestBody MauSacRequest mauSac) {
        return ResponseEntity.ok(mauSacService.createMauSac(mauSac));
    }

    @PutMapping("/update/{ma}")
    public ResponseEntity<MauSacResponse> updateMauSac(@PathVariable String ma, @RequestBody MauSacRequest mauSac) {
        return ResponseEntity.ok(mauSacService.updateMauSac(ma, mauSac));
    }

    @DeleteMapping("/delete/{ma}")
    public ResponseEntity<Void> deleteMauSac(@PathVariable String ma) {
        mauSacService.deleteMauSac(ma);
        return ResponseEntity.ok().build();
    }

}
