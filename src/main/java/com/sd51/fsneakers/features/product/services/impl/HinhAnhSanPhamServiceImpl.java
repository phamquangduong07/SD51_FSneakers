package com.sd51.fsneakers.features.product.services.impl;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.*;

import com.sd51.fsneakers.features.product.mapper.HinhAnhSanPhamMapper;
import com.sd51.fsneakers.features.product.dto.request.HinhAnhSanPhamRequest;
import com.sd51.fsneakers.features.product.dto.response.HinhAnhSanPhamResponse;
import com.sd51.fsneakers.features.product.entity.SanPhamChiTiet;
import com.sd51.fsneakers.features.product.repositories.SanPhamChiTietRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sd51.fsneakers.features.product.entity.HinhAnhSanPham;
import com.sd51.fsneakers.features.product.repositories.HinhAnhSanPhamRepository;
import com.sd51.fsneakers.features.product.services.CloudinaryService;
import com.sd51.fsneakers.features.product.services.HinhAnhSanPhamService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class HinhAnhSanPhamServiceImpl implements HinhAnhSanPhamService {

    HinhAnhSanPhamRepository hinhAnhSanPhamRepository;

    SanPhamChiTietRepository sanPhamChiTietRepository;

    CloudinaryService cloudinaryService;

    HinhAnhSanPhamMapper hinhAnhSanPhamMapper;

    static final String UPLOAD_DIR = "src/main/resources/static/uploads/";

    @Override
    public List<HinhAnhSanPhamResponse> getAllHinhAnhBySanPham() {
        return hinhAnhSanPhamRepository.findAll().stream().map(hinhAnhSanPhamMapper::toResponse).toList();
    }

    @Override
    public Page<HinhAnhSanPhamResponse> getAllHinhAnhSanPhamPage(Pageable pageable) {
        return hinhAnhSanPhamRepository.getAllPage(pageable).map(hinhAnhSanPhamMapper::toResponse);
    }

    @Override
    public Page<HinhAnhSanPhamResponse> searchHinhAnhSanPham(String keyword, Integer trangThai, Pageable pageable) {
        return hinhAnhSanPhamRepository.searchHinhAnhSanPham(keyword, trangThai, pageable).map(hinhAnhSanPhamMapper::toResponse);
    }

    @Override
    public List<HinhAnhSanPham> getImagesByChiTietId(UUID chiTietId) {
        return hinhAnhSanPhamRepository.findByChiTietSanPham_Id(chiTietId);
    }

    @Override
    public HinhAnhSanPham findByMa(String ma) {
        return hinhAnhSanPhamRepository.findByMa(ma);
    }

    @Override
    public HinhAnhSanPham findById(UUID id) {
        return hinhAnhSanPhamRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy dữ liệu với id = " + id));
    }

    @Override
    public HinhAnhSanPhamResponse createHinhAnhSanPham(HinhAnhSanPhamRequest request) {
        if (hinhAnhSanPhamRepository.findByMa(request.getMa()) != null) {
            throw new RuntimeException("Mã hình ảnh sản phẩm '" + request.getMa() + "' đã tồn tại.");
        }
        HinhAnhSanPham hinhAnhSanPham = hinhAnhSanPhamMapper.toEntity(request);
        hinhAnhSanPhamRepository.save(hinhAnhSanPham);
        return hinhAnhSanPhamMapper.toResponse(hinhAnhSanPham);
    }

    @Override
    public HinhAnhSanPhamResponse updateHinhAnhSanPham(UUID id, HinhAnhSanPhamRequest request) {
        HinhAnhSanPham existing = findById(id);
        if (existing == null) {
            throw new RuntimeException("Id hình ảnh sản phẩm '" + id + "' không tồn tại!");
        }
        if (!existing.getMa().equals(request.getMa())) {
            if (findByMa(request.getMa()) != null) {
                throw new RuntimeException("Mã hình ảnh sản phẩm '" + request.getMa() + "' đã tồn tại!");
            }
        }
        hinhAnhSanPhamMapper.toUpdate(existing, request);
        HinhAnhSanPham update = hinhAnhSanPhamRepository.save(existing);
        return hinhAnhSanPhamMapper.toResponse(update);
    }

    @Override
    public HinhAnhSanPhamResponse deleteHinhAnhSanPham(UUID id) {
        HinhAnhSanPham existing = findById(id);
        if (existing == null) {
            throw new RuntimeException("Mã hình ảnh sản phẩm '" + id + "' không tồn tại!");
        }
        HinhAnhSanPhamResponse hinhAnhSanPhamResponse = hinhAnhSanPhamMapper.toResponse(existing);
        hinhAnhSanPhamRepository.delete(existing);
        return hinhAnhSanPhamResponse;
    }


    @Override
    public List<HinhAnhSanPham> updloadImage(UUID chiTietSanPhamId, List<MultipartFile> files) throws IOException {
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.findById(chiTietSanPhamId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm chi tiết"));

        if (files == null || files.isEmpty()) {
            throw new RuntimeException("Không có file nào được tải lên");
        }

        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        List<HinhAnhSanPham> results = new ArrayList<>();

        for (MultipartFile file : files) {
            if (file.isEmpty())
                continue;
            if (file.getSize() > 10 * 1024 * 1024) {
                throw new IOException("File quá lớn (>10MB)");
            }
            if (!Objects.requireNonNull(file.getContentType()).startsWith("image/")) {
                throw new IOException("File không phải hình ảnh");
            }

            // Upload lên Cloudinary thay vì lưu local
            Map uploadResult = cloudinaryService.uploadFileImage(file);
            String imageUrl = (String) uploadResult.get("secure_url");

            HinhAnhSanPham img = new HinhAnhSanPham();
            img.setMa("IMG-" + UUID.randomUUID().toString().substring(0, 8));
            img.setTen(file.getOriginalFilename());
            img.setUrl(imageUrl);
            img.setTrangThai(1);
            img.setNgayTao(LocalDateTime.now());
            img.setChiTietSanPham(sanPhamChiTiet);

            results.add(hinhAnhSanPhamRepository.save(img));
        }

        return results;
    }


    @Override
    public HinhAnhSanPham uploadAndSaveImage(MultipartFile file, String ma, String ten, Integer trangThai,
                                             UUID chiTietSanPhamId)
            throws IOException {

        if (hinhAnhSanPhamRepository.findByMa(ma) != null) {
            throw new RuntimeException("Mã hình ảnh sản phẩm '" + ma + "' đã tồn tại.");
        }

        Map uploadResult = cloudinaryService.uploadFileImage(file);
        String imageUrl = (String) uploadResult.get("secure_url");

        // Tìm sản phẩm chi tiết
        SanPhamChiTiet chiTietSanPham = sanPhamChiTietRepository.findById(chiTietSanPhamId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm chi tiết"));

        HinhAnhSanPham hinhAnhSanPham = HinhAnhSanPham.builder()
                .ma(ma)
                .ten(ten)
                .url(imageUrl)
                .trangThai(trangThai)
                .chiTietSanPham(chiTietSanPham) // Gắn sản phẩm chi tiết
                .build();

        return hinhAnhSanPhamRepository.save(hinhAnhSanPham);
    }

}
