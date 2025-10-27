package com.sd51.fsneakers.features.product.services.impl;

import java.util.List;
import java.util.UUID;

import com.sd51.fsneakers.features.product.mapper.SanPhamMapper;
import com.sd51.fsneakers.features.product.dto.request.SanPhamRequest;
import com.sd51.fsneakers.features.product.dto.response.SanPhamResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sd51.fsneakers.features.product.entity.SanPham;
import com.sd51.fsneakers.features.product.repositories.SanPhamRepository;
import com.sd51.fsneakers.features.product.services.SanPhamService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SanPhamServiceImpl implements SanPhamService {

    SanPhamRepository sanPhamRepository;
    SanPhamMapper sanPhamMapper;

    @Override
    public List<SanPhamResponse> getAllSanPham() {
        return sanPhamRepository.findAll().stream().map(sanPhamMapper::toResponse).toList();
    }

    @Override
    public Page<SanPhamResponse> getAllSanPhamPage(Pageable pageable) {
        return sanPhamRepository.getAllPage(pageable).map(sanPhamMapper::toResponse);
    }

    @Override
    public Page<SanPhamResponse> searchSanPham(String keyword, Integer trangThai, Pageable pageable) {
        return sanPhamRepository.searchSanPham(keyword, trangThai, pageable).map(sanPhamMapper::toResponse);
    }

    @Override
    public SanPham findByMa(String maSanPham) {
        return sanPhamRepository.findByMa(maSanPham);
    }

    @Override
    public SanPham findById(UUID id) {
        return sanPhamRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy dữ liệu với id = " + id));
    }

    @Override
    public SanPhamResponse createSanPham(SanPhamRequest request) {
        if (sanPhamRepository.findByMa(request.getMa()) != null) {
            throw new RuntimeException("Mã sản phẩm '" + request.getMa() + "' đã tồn tại");
        }
        SanPham sanPham = sanPhamMapper.toEntity(request);
        sanPhamRepository.save(sanPham);
        return sanPhamMapper.toResponse(sanPham);
    }

    @Override
    public SanPhamResponse updateSanPham(UUID id, SanPhamRequest request) {
        SanPham existing = findById(id);
        if (existing == null) {
            throw new RuntimeException("Id sản phẩm '" + id + "' không tồn tại.");
        }
        if (!existing.getMa().equals(request.getMa())) {
            if (findByMa(request.getMa()) != null) {
                throw new RuntimeException("Mã sản phẩm '" + request.getMa() + "' đã tồn tại!");
            }
        }
        // Cập nhật các thuộc tính của existing với giá trị từ updateSanPham
        sanPhamMapper.toUpdate(existing, request);
        SanPham sanPham = sanPhamRepository.save(existing);
        return sanPhamMapper.toResponse(sanPham);
    }

    @Override
    public SanPhamResponse deleteSanPham(UUID id) {
        SanPham existing = findById(id);
        if (existing == null) {
            throw new IllegalArgumentException("Id sản phẩm '" + id + "' không tồn tại.");
        }
        SanPhamResponse sanPhamResponse = sanPhamMapper.toResponse(existing);
        sanPhamRepository.delete(existing);
        return sanPhamResponse;
    }


}
