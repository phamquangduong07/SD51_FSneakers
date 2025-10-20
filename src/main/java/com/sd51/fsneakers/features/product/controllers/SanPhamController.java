package com.sd51.fsneakers.features.product.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sd51.fsneakers.features.product.entity.SanPham;
import com.sd51.fsneakers.features.product.services.SanPhamService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("api/v1/san-pham")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SanPhamController {

    SanPhamService sanPhamService;

    @GetMapping({ "", "/" })
    public List<SanPham> getAllSanPham() {
        return ResponseEntity.ok(sanPhamService.getAllSanPham()).getBody();
    }

    @PostMapping("/add")
    public SanPham createSanPham(@RequestBody SanPham sanPham) {
        return ResponseEntity.ok(sanPhamService.createSanPham(sanPham)).getBody();
    }

    @PutMapping("/update/{ma}")
    public SanPham updateSanPham(@RequestBody SanPham sanPham, @PathVariable String ma) {
        return ResponseEntity.ok(sanPhamService.updateSanPham(ma, sanPham)).getBody();
    }

    @DeleteMapping("/delete/{ma}")
    public SanPham deleteSanPham(@PathVariable String ma) {
        return ResponseEntity.ok(sanPhamService.deleteSanPham(ma)).getBody();
    }

    @GetMapping("/page")
    public Page<SanPham> getAllSanPhamPage(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(sanPhamService.getAllSanPhamPage(pageable)).getBody();
    }

    @GetMapping("/search")
    public Page<SanPham> searchSanPham(@RequestParam (required = false) String keyword,
                                     @RequestParam (required = false) Integer trangThai,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return  sanPhamService.searchSanPham(keyword, trangThai, pageable);
    }

}
