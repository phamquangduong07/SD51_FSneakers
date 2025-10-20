package com.sd51.fsneakers.features.product.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sd51.fsneakers.features.product.entity.SanPhamChiTiet;
import com.sd51.fsneakers.features.product.services.SanPhamChiTietService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("v1/api/san-pham-chi-tiet")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SanPhamChiTietController {

    SanPhamChiTietService sanPhamChiTietService;

    @GetMapping({"", "/"})
    public List<SanPhamChiTiet> getAllSanPhamChiTiet() {
        return ResponseEntity.ok(sanPhamChiTietService.getAllSanPhamChiTiet()).getBody();
    }

    @PostMapping("/add")
    public SanPhamChiTiet createSanPhamChiTiet(@RequestBody SanPhamChiTiet sanPhamChiTiet) {
        return ResponseEntity.ok(sanPhamChiTietService.createSanPhamChiTiet(sanPhamChiTiet)).getBody();
    }

    @PutMapping("/update/{ma}")
    public SanPhamChiTiet updateSanPhamChiTiet(@RequestBody SanPhamChiTiet sanPhamChiTietUpdate, @PathVariable String ma) {
        return ResponseEntity.ok(sanPhamChiTietService.updateSanPhamChiTiet(ma, sanPhamChiTietUpdate)).getBody();
    }

    @DeleteMapping("/delete/{ma}")
    public SanPhamChiTiet deleteSanPhamChiTiet(@PathVariable String ma) {
        return ResponseEntity.ok(sanPhamChiTietService.deleteSanPhamChiTiet(ma)).getBody();
    }
}
