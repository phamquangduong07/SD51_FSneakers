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

import com.sd51.fsneakers.features.product.entity.HinhAnhSanPham;
import com.sd51.fsneakers.features.product.services.HinhAnhSanPhamService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("api/v1/hinh-anh-san-pham")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HinhAnhSanPhamController {

    HinhAnhSanPhamService hinhAnhSanPhamService;

    @GetMapping({ "", "/" })
    public List<HinhAnhSanPham> getAllHinhAnhBySanPham() {
        return ResponseEntity.ok(hinhAnhSanPhamService.getAllHinhAnhBySanPham()).getBody();
    }

    @PostMapping("/add")
    public HinhAnhSanPham createHinhAnhSanPham(@RequestBody HinhAnhSanPham hinhAnhSanPham) {
        return ResponseEntity.ok(hinhAnhSanPhamService.createHinhAnhSanPham(hinhAnhSanPham)).getBody();
    }

    @PutMapping("/update/{ma}")
    public HinhAnhSanPham updateHinhAnhSanPham(@RequestBody HinhAnhSanPham hinhAnhSanPhamUpdate,
            @PathVariable String ma) {
        return ResponseEntity.ok(hinhAnhSanPhamService.updateHinhAnhSanPham(ma, hinhAnhSanPhamUpdate)).getBody();
    }

    @DeleteMapping("/delete/{ma}")
    public HinhAnhSanPham deleteHinhAnhSanPham(@PathVariable String ma) {
        return ResponseEntity.ok(hinhAnhSanPhamService.deleteHinhAnhSanPham(ma)).getBody();
    }
}
