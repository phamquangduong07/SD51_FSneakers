package com.sd51.fsneakers.features.product.controllers;

import java.util.List;
import java.util.UUID;

import com.sd51.fsneakers.features.product.dto.request.HinhAnhSanPhamRequest;
import com.sd51.fsneakers.features.product.dto.response.HinhAnhSanPhamResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.sd51.fsneakers.features.product.entity.HinhAnhSanPham;
import com.sd51.fsneakers.features.product.services.HinhAnhSanPhamService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/hinh-anh-san-pham")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HinhAnhSanPhamController {

    HinhAnhSanPhamService hinhAnhSanPhamService;

    @GetMapping({"", "/"})
    public ResponseEntity<List<HinhAnhSanPhamResponse>> getAllHinhAnhBySanPham() {
        return ResponseEntity.ok(hinhAnhSanPhamService.getAllHinhAnhBySanPham());
    }

    @GetMapping("/page")
    public ResponseEntity<Page<HinhAnhSanPhamResponse>> getAllHinhAnhSanPhamPage(@RequestParam(defaultValue = "0") int page,
                                                                                 @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(hinhAnhSanPhamService.getAllHinhAnhSanPhamPage(pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<HinhAnhSanPhamResponse>> searchHinhAnhSanPham(@RequestParam(required = false) String keyword,
                                                                             @RequestParam(required = false) Integer trangThai,
                                                                             @RequestParam(defaultValue = "0") int page,
                                                                             @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(hinhAnhSanPhamService.searchHinhAnhSanPham(keyword, trangThai, pageable));
    }

    @PostMapping("/add")
    public ResponseEntity<HinhAnhSanPhamResponse> createHinhAnhSanPham(@RequestBody HinhAnhSanPhamRequest hinhAnhSanPham) {
        return ResponseEntity.ok(hinhAnhSanPhamService.createHinhAnhSanPham(hinhAnhSanPham));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HinhAnhSanPhamResponse> updateHinhAnhSanPham(@RequestBody HinhAnhSanPhamRequest hinhAnhSanPhamUpdate,
                                                                       @PathVariable UUID id) {
        return ResponseEntity.ok(hinhAnhSanPhamService.updateHinhAnhSanPham(id, hinhAnhSanPhamUpdate));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HinhAnhSanPhamResponse> deleteHinhAnhSanPham(@PathVariable UUID id) {

        return ResponseEntity.ok(hinhAnhSanPhamService.deleteHinhAnhSanPham(id));
    }

    // Upload image Cloudinary và upload(update) database
    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("ma") String ma,
            @RequestParam("ten") String ten,
            @RequestParam("trangThai") Integer trangThai,
            @RequestParam("chiTietSanPham") UUID chiTietSanPhamId) {
        try {
            HinhAnhSanPham result = hinhAnhSanPhamService.uploadAndSaveImage(file, ma, ten, trangThai,
                    chiTietSanPhamId);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Upload multiple images for a specific ChiTietSanPham ID
    // Upload Cloudinary và upload database
    @PostMapping(value = "/upload/{chiTietId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> upload(
            @PathVariable UUID chiTietId,
            @ModelAttribute("files") List<MultipartFile> files) {
        try {
            List<HinhAnhSanPham> result = hinhAnhSanPhamService.updloadImage(chiTietId, files);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Lấy danh sách hình ảnh theo ChiTietSanPham ID
    @GetMapping("/{chiTietId}")
    public ResponseEntity<?> getImages(@PathVariable UUID chiTietId) {
        try {
            List<HinhAnhSanPham> images = hinhAnhSanPhamService.getImagesByChiTietId(chiTietId);
            return ResponseEntity.ok(images);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
