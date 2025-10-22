package com.sd51.fsneakers.features.product.services.impl;

import java.util.List;

import com.sd51.fsneakers.features.mapper.SanPhamMapper;
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

    @Override
    public List<SanPhamResponse> getAllSanPham() {
        return sanPhamRepository.findAll().stream().map(SanPhamMapper::toResponse).toList();
    }

    @Override
    public Page<SanPhamResponse> getAllSanPhamPage(Pageable pageable) {
        return sanPhamRepository.getAllPage(pageable).map(SanPhamMapper::toResponse);
    }

    @Override
    public Page<SanPhamResponse> searchSanPham(String keyword, Integer trangThai, Pageable pageable) {
        return sanPhamRepository.searchSanPham(keyword, trangThai, pageable).map(SanPhamMapper::toResponse);
    }

    @Override
    public SanPham findByMa(String maSanPham) {
        return sanPhamRepository.findByMa(maSanPham);
    }

    @Override
    public SanPhamResponse createSanPham(SanPhamRequest request) {
        if (sanPhamRepository.findByMa(request.getMa()) != null) {
            throw new RuntimeException("Mã sản phẩm '" + request.getMa() + "' đã tồn tại");
        }
        SanPham sanPham = SanPhamMapper.toEntity(request);
        sanPhamRepository.save(sanPham);
        return SanPhamMapper.toResponse(sanPham);
    }

    @Override
    public SanPhamResponse updateSanPham(String ma, SanPhamRequest request) {
        SanPham existing = findByMa(ma);
        if (existing == null) {
            throw new RuntimeException("Mã sản phẩm '" + ma + "' không tồn tại.");
        }
        if (!request.getMa().equals(ma)) {
            if (findByMa(request.getMa()) != null) {
                throw new RuntimeException("Mã sản phẩm '" + request.getMa() + "' đã tồn tại!");
            }
        }
        // Cập nhật các thuộc tính của existing với giá trị từ updateSanPham
        SanPhamMapper.toUpdate(existing, request);
        SanPham sanPham = sanPhamRepository.save(existing);
        return SanPhamMapper.toResponse(sanPham);
    }

    @Override
    public void deleteSanPham(String maSanPham) {
        SanPham existing = findByMa(maSanPham);
        if (existing == null) {
            throw new IllegalArgumentException("Mã sản phẩm '" + maSanPham + "' không tồn tại.");
        }

        sanPhamRepository.delete(existing);
    }


}
