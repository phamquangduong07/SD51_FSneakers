package com.sd51.fsneakers.features.product.controllers;

import com.sd51.fsneakers.features.product.dto.request.DanhMucRequest;
import com.sd51.fsneakers.features.product.dto.response.DanhMucResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sd51.fsneakers.features.product.services.DanhMucService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @GetMapping({"", "/"})
    public ResponseEntity<List<DanhMucResponse>> getAll() {
        return ResponseEntity.ok(danhMucService.getAllDanhMuc());
    }

    @GetMapping("/page")
    public ResponseEntity<Page<DanhMucResponse>> getAllDanhMucPage(@RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(danhMucService.getAllDanhMucPage(pageable));

    }

    @GetMapping("/search")
    public ResponseEntity<Page<DanhMucResponse>> searchDanhMuc(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer trangThai,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(danhMucService.searchDanhMuc(keyword, trangThai, pageable));
    }

    @PostMapping("/add")
    public ResponseEntity<DanhMucResponse> createDanhMuc(@RequestBody DanhMucRequest request) {
        return ResponseEntity.ok(danhMucService.createDanhMuc(request));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DanhMucResponse> updateDanhMuc(@PathVariable UUID id, @RequestBody DanhMucRequest request) {
        DanhMucResponse update = danhMucService.updateDanhMucByMa(id, request);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DanhMucResponse> deleteDanhMuc(@PathVariable UUID id) {
        return ResponseEntity.ok(danhMucService.deleteDanhMuc(id));
    }

}
