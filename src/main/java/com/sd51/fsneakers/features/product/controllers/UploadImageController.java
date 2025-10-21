package com.sd51.fsneakers.features.product.controllers;

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

@RestController
@RequestMapping("api/v1/images")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UploadImageController {

    CloudinaryServiceImpl cloudinaryService;

    // Upload image to Cloudinary and return the URL
    // Tải lên ảnh với cloudinary và trả về URL
    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
        try {

            String imageUrl = cloudinaryService.uploadFile(file);
            return ResponseEntity.ok(imageUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Upload thất bại: " + e.getMessage());
        }
    }

}
