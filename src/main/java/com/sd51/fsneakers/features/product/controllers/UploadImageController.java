package com.sd51.fsneakers.features.product.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sd51.fsneakers.features.product.services.impl.CloudinaryServiceImpl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@RestController
@RequestMapping("api/v1/images")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UploadImageController {

    CloudinaryServiceImpl cloudinaryService;
    Cloudinary cloudinary;

    // Upload image to Cloudinary and return the URL
    // Tải lên ảnh với cloudinary và trả về URL
//    @PostMapping("/upload")
//    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
//        try {
//
//            String imageUrl = cloudinaryService.uploadFile(file);
//            return ResponseEntity.ok(imageUrl);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Upload thất bại: " + e.getMessage());
//        }
//    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("File trống!");
            }

            // Upload lên Cloudinary
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                    "folder", "fsneakers/uploads", // thư mục Cloudinary của bạn
                    "resource_type", "image"
            ));

            log.info("Upload success: {}", uploadResult.get("secure_url"));
            return ResponseEntity.ok(Map.of(
                    "url", uploadResult.get("secure_url"),
                    "public_id", uploadResult.get("public_id")
            ));

        } catch (Exception e) {
            log.error("Upload thất bại:", e);
            return ResponseEntity.internalServerError().body("Lỗi khi upload ảnh: " + e.getMessage());
        }
    }
}
