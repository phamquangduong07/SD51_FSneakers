package com.sd51.fsneakers.features.product.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sd51.fsneakers.features.product.entity.DanhMuc;
import com.sd51.fsneakers.features.product.services.DanhMucService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/v1/danh-muc")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DanhMucController {

    DanhMucService danhMucService;

    @GetMapping({ "", "/" })
    public List<DanhMuc> getAll() {
        return ResponseEntity.ok(danhMucService.getAllDanhMuc()).getBody();
    }

    @PostMapping("/add")
    public DanhMuc createDanhMuc(@RequestBody DanhMuc danhMuc) {
        return ResponseEntity.ok(danhMucService.createDanhMuc(danhMuc)).getBody();
    }

    @PutMapping("/update/{ma}")
    public DanhMuc updateDanhMuc(@PathVariable String ma, @RequestBody DanhMuc danhMuc) {
        return ResponseEntity.ok(danhMucService.updateDanhMucByMa(ma, danhMuc)).getBody();
    }

    @DeleteMapping("/delete/{ma}")
    public DanhMuc deleteDanhMuc(@PathVariable String ma) {
        return ResponseEntity.ok(danhMucService.deleteDanhMuc(ma)).getBody();
    }

    @GetMapping("/page")
    public Page<DanhMuc> getAllDanhMucPage(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(danhMucService.getAllDanhMucPage(pageable)).getBody();

    }

    @GetMapping("/search")
    public Page<DanhMuc> searchDanhMuc(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer trangThai,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity
                .ok(danhMucService.searchDanhMuc(keyword, trangThai, pageable))
                .getBody();
    }

}
