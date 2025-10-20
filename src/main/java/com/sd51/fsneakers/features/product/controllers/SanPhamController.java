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

import com.sd51.fsneakers.features.product.entity.SanPham;
import com.sd51.fsneakers.features.product.services.SanPhamService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("v1/api/san-pham")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SanPhamController {

    SanPhamService sanPhamService;

    @GetMapping({"", "/"})
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

}
