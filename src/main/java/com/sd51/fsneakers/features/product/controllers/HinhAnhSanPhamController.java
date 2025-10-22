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

    @GetMapping({ "", "/" })
    public List<HinhAnhSanPhamResponse> getAllHinhAnhBySanPham() {
        return ResponseEntity.ok(hinhAnhSanPhamService.getAllHinhAnhBySanPham()).getBody();
    }

    @GetMapping("/page")
    public Page<HinhAnhSanPhamResponse> getAllHinhAnhSanPhamPage(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(hinhAnhSanPhamService.getAllHinhAnhSanPhamPage(pageable)).getBody();
    }

    @GetMapping("/search")
    public Page<HinhAnhSanPhamResponse> searchHinhAnhSanPham(@RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer trangThai,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return hinhAnhSanPhamService.searchHinhAnhSanPham(keyword, trangThai, pageable);
    }

    @PostMapping("/add")
    public HinhAnhSanPhamResponse createHinhAnhSanPham(@RequestBody HinhAnhSanPhamRequest hinhAnhSanPham) {
        return ResponseEntity.ok(hinhAnhSanPhamService.createHinhAnhSanPham(hinhAnhSanPham)).getBody();
    }

    @PutMapping("/update/{ma}")
    public HinhAnhSanPhamResponse updateHinhAnhSanPham(@RequestBody HinhAnhSanPhamRequest hinhAnhSanPhamUpdate,
            @PathVariable String ma) {
        return ResponseEntity.ok(hinhAnhSanPhamService.updateHinhAnhSanPham(ma, hinhAnhSanPhamUpdate)).getBody();
    }

    @DeleteMapping("/delete/{ma}")
    public ResponseEntity<Void> deleteHinhAnhSanPham(@PathVariable String ma) {
        hinhAnhSanPhamService.deleteHinhAnhSanPham(ma);
        return ResponseEntity.ok().build();
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
