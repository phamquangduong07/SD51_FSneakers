package com.sd51.fsneakers.features.product.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sd51.fsneakers.features.product.entity.SanPhamChiTiet;
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

    @GetMapping({ "", "/" })
    public List<SanPhamChiTiet> getAllSanPhamChiTiet() {
        return ResponseEntity.ok(sanPhamChiTietService.getAllSanPhamChiTiet()).getBody();
    }

    @PostMapping("/add")
    public SanPhamChiTiet createSanPhamChiTiet(@RequestBody SanPhamChiTiet sanPhamChiTiet) {
        return ResponseEntity.ok(sanPhamChiTietService.createSanPhamChiTiet(sanPhamChiTiet)).getBody();
    }

    @PutMapping("/update/{ma}")
    public SanPhamChiTiet updateSanPhamChiTiet(@RequestBody SanPhamChiTiet sanPhamChiTietUpdate,
            @PathVariable String ma) {
        return ResponseEntity.ok(sanPhamChiTietService.updateSanPhamChiTiet(ma, sanPhamChiTietUpdate)).getBody();
    }

    @DeleteMapping("/delete/{ma}")
    public SanPhamChiTiet deleteSanPhamChiTiet(@PathVariable String ma) {
        return ResponseEntity.ok(sanPhamChiTietService.deleteSanPhamChiTiet(ma)).getBody();
    }

    @GetMapping("/page")
    public Page<SanPhamChiTiet> getAllSanPhamChiTietPage(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(sanPhamChiTietService.getAllSanPhamChiTietPage(pageable)).getBody();
    }

    @GetMapping("/search")
    public Page<SanPhamChiTiet> searchSanPhamChiTiet(@RequestParam (required = false) String keyword,
                                     @RequestParam (required = false) Integer trangThai,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return  sanPhamChiTietService.searchSanPhamChiTiet(keyword, trangThai, pageable);
    }
}
