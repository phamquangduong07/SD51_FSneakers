package com.sd51.fsneakers.features.product.services.impl;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;


import com.sd51.fsneakers.features.product.entity.SanPhamChiTiet;
import com.sd51.fsneakers.features.product.repositories.SanPhamChiTietRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sd51.fsneakers.features.product.entity.HinhAnhSanPham;
import com.sd51.fsneakers.features.product.repositories.HinhAnhSanPhamRepository;
import com.sd51.fsneakers.features.product.services.HinhAnhSanPhamService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HinhAnhSanPhamServiceImpl implements HinhAnhSanPhamService {

    HinhAnhSanPhamRepository hinhAnhSanPhamRepository;
    SanPhamChiTietRepository sanPhamChiTietRepository;

    static final String UPLOAD_DIR = "src/main/resources/static/uploads/";



    @Override
    public List<HinhAnhSanPham> getAllHinhAnhBySanPham() {
        return hinhAnhSanPhamRepository.findAll();
    }

    @Override
    public HinhAnhSanPham findByMa(String ma) {
        return hinhAnhSanPhamRepository.findByMa(ma);
    }

    @Override
    public HinhAnhSanPham createHinhAnhSanPham(HinhAnhSanPham hinhAnhSanPham) {
        if (hinhAnhSanPhamRepository.findByMa(hinhAnhSanPham.getMa()) != null) {
            throw new IllegalArgumentException("Mã hình ảnh sản phẩm '" + hinhAnhSanPham.getMa() + "' đã tồn tại.");
        }

        return hinhAnhSanPhamRepository.save(hinhAnhSanPham);
    }

    @Override
    public HinhAnhSanPham updateHinhAnhSanPham(String ma, HinhAnhSanPham hinhAnhSanPhamUpdate) {
        HinhAnhSanPham existing = findByMa(ma);
        if (existing == null) {
            throw new RuntimeException("Mã hình ảnh sản phẩm '" + ma + "' không tồn tại!");

        }
        if (!hinhAnhSanPhamUpdate.getMa().equals(ma)) {
            if (findByMa(hinhAnhSanPhamUpdate.getMa()) != null) {
                throw new RuntimeException("Mã hình ảnh sản phẩm '" + hinhAnhSanPhamUpdate.getMa() + "' đã tồn tại!");
            } else {

            }
        }
        // Cập nhật các thuộc tính của existing với giá trị từ hinhAnhSanPhamUpdate
        existing.setMa(hinhAnhSanPhamUpdate.getMa());
        existing.setTen(hinhAnhSanPhamUpdate.getTen());
        existing.setUrl(hinhAnhSanPhamUpdate.getUrl());
        existing.setTrangThai(hinhAnhSanPhamUpdate.getTrangThai());
        existing.setChiTietSanPham(hinhAnhSanPhamUpdate.getChiTietSanPham());
        return hinhAnhSanPhamRepository.save(existing);
    }

    @Override
    public HinhAnhSanPham deleteHinhAnhSanPham(String ma) {
        HinhAnhSanPham existing = findByMa(ma);
        if (existing == null) {
            throw new RuntimeException("Mã hình ảnh sản phẩm '" + ma + "' không tồn tại!");
        }
        hinhAnhSanPhamRepository.delete(existing);
        return existing;
    }

    @Override
    public Page<HinhAnhSanPham> getAllHinhAnhSanPhamPage(Pageable pageable) {
        return hinhAnhSanPhamRepository.getAllPage(pageable);
    }

    @Override
    public Page<HinhAnhSanPham> searchHinhAnhSanPham(String keyword, Integer trangThai, Pageable pageable) {
        return hinhAnhSanPhamRepository.searchHinhAnhSanPham(keyword, trangThai, pageable);
    }

    @Override
    public List<HinhAnhSanPham> updloadImage(UUID chiTietSanPhamId, List<MultipartFile> files) throws IOException  {
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.findById(chiTietSanPhamId).orElseThrow(() ->
                new RuntimeException("Không tìm thấy sản phẩm chi tiết"));
        if (files == null || files.isEmpty()) {
            throw new RuntimeException("Không có file nào được tải lên");
        }

        // Tạo thư mục nếu chưa có
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        List<HinhAnhSanPham> results = new ArrayList<>();

        for (MultipartFile file : files) {
            if (file.isEmpty()) continue;
            if (file.getSize() > 10 * 1024 * 1024) {
                throw new IOException("File quá lớn (>10MB)");
            }
            if (!Objects.requireNonNull(file.getContentType()).startsWith("image/")) {
                throw new IOException("File không phải hình ảnh");
            }

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path destination = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);

            HinhAnhSanPham img = HinhAnhSanPham.builder()
                    .ma("IMG-" + UUID.randomUUID().toString().substring(0, 8))
                    .ten(file.getOriginalFilename())
                    .url("/uploads/" + fileName)
                    .trangThai(1)
                    .chiTietSanPham(sanPhamChiTiet)
                    .build();

            results.add(hinhAnhSanPhamRepository.save(img));
        }

        return results;
    }

    @Override
    public List<HinhAnhSanPham> getImagesByChiTietId(UUID chiTietId) {
        return hinhAnhSanPhamRepository.findByChiTietSanPham_Id(chiTietId);
    }

}
