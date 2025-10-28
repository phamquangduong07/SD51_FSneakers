package com.sd51.fsneakers.features.product.controllers;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import com.google.zxing.WriterException;
import com.sd51.fsneakers.features.product.dto.request.SanPhamChiTietRequest;
import com.sd51.fsneakers.features.product.dto.response.SanPhamChiTietResponse;
import com.sd51.fsneakers.features.product.services.QRCodeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sd51.fsneakers.features.product.services.SanPhamChiTietService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("api/v1/san-pham-chi-tiet")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SanPhamChiTietController {

    SanPhamChiTietService sanPhamChiTietService;
    QRCodeService qrCodeService;



    @GetMapping({"", "/"})
    public ResponseEntity<List<SanPhamChiTietResponse>> getAllSanPhamChiTiet() {
        return ResponseEntity.ok(sanPhamChiTietService.getAllSanPhamChiTiet());
    }

    @GetMapping("/page")
    public ResponseEntity<Page<SanPhamChiTietResponse>> getAllSanPhamChiTietPage(@RequestParam(defaultValue = "0") int page,
                                                                                 @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(sanPhamChiTietService.getAllSanPhamChiTietPage(pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<SanPhamChiTietResponse>> searchSanPhamChiTiet(@RequestParam(required = false) String keyword,
                                                                             @RequestParam(required = false) Integer trangThai,
                                                                             @RequestParam(defaultValue = "0") int page,
                                                                             @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(sanPhamChiTietService.searchSanPhamChiTiet(keyword, trangThai, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SanPhamChiTietResponse> getByIdSanPhamChiTiet(@PathVariable UUID id) {
        return ResponseEntity.ok(sanPhamChiTietService.getById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<SanPhamChiTietResponse> createSanPhamChiTiet(@RequestBody SanPhamChiTietRequest sanPhamChiTiet) throws IOException, WriterException {
        return ResponseEntity.ok(sanPhamChiTietService.createSanPhamChiTiet(sanPhamChiTiet));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SanPhamChiTietResponse> updateSanPhamChiTiet(@RequestBody SanPhamChiTietRequest sanPhamChiTietUpdate,
                                                                       @PathVariable UUID id) {

        return ResponseEntity.ok(sanPhamChiTietService.updateSanPhamChiTiet(id, sanPhamChiTietUpdate));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SanPhamChiTietResponse> deleteSanPhamChiTiet(@PathVariable UUID id) {
        return ResponseEntity.ok(sanPhamChiTietService.deleteSanPhamChiTiet(id));
    }
}
