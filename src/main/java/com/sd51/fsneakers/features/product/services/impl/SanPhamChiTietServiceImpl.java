package com.sd51.fsneakers.features.product.services.impl;

import java.util.List;
import java.util.UUID;

import com.sd51.fsneakers.features.product.mapper.SanPhamChiTietMapper;
import com.sd51.fsneakers.features.product.dto.request.SanPhamChiTietRequest;
import com.sd51.fsneakers.features.product.dto.response.SanPhamChiTietResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sd51.fsneakers.features.product.entity.SanPhamChiTiet;
import com.sd51.fsneakers.features.product.repositories.SanPhamChiTietRepository;
import com.sd51.fsneakers.features.product.services.SanPhamChiTietService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class SanPhamChiTietServiceImpl implements SanPhamChiTietService {

    SanPhamChiTietRepository sanPhamChiTietRepository;

    SanPhamChiTietMapper sanPhamChiTietMapper;

    @Override
    public List<SanPhamChiTietResponse> getAllSanPhamChiTiet() {
        return sanPhamChiTietRepository.findAll().stream().map(sanPhamChiTietMapper::toResponse).toList();
    }

    @Override
    public Page<SanPhamChiTietResponse> getAllSanPhamChiTietPage(Pageable pageable) {
        return sanPhamChiTietRepository.getAllPage(pageable).map(sanPhamChiTietMapper::toResponse);
    }

    @Override
    public Page<SanPhamChiTietResponse> searchSanPhamChiTiet(String keyword, Integer trangThai, Pageable pageable) {
        return sanPhamChiTietRepository.searchSanPhamChiTiet(keyword, trangThai, pageable)
                .map(sanPhamChiTietMapper::toResponse);
    }

    @Override
    public SanPhamChiTiet findByMa(String maSanPhamChiTiet) {
        return sanPhamChiTietRepository.findByMa(maSanPhamChiTiet);
    }

    @Override
    public SanPhamChiTiet findById(UUID id) {
        return sanPhamChiTietRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Không tìm thấy dữ liệu với id = " + id));
    }

    @Override
    public SanPhamChiTietResponse getById(UUID id) {
        SanPhamChiTiet entity = findById(id);
        return sanPhamChiTietMapper.toResponse(entity);
    }

    @Override
    public SanPhamChiTietResponse createSanPhamChiTiet(SanPhamChiTietRequest request) {
        if (sanPhamChiTietRepository.findByMa(request.getMa()) != null) {
            throw new RuntimeException("Mã sản phẩm chi tiết '" + request.getMa() + "' đã tồn tại.");
        }

        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietMapper.toEntity(request);
        // Sinh mã, QR ngẫu nhiên
        String maQr = "QR-" + UUID.randomUUID().toString().substring(0, 8);
        sanPhamChiTiet.setMaQr(maQr);
        sanPhamChiTietRepository.save(sanPhamChiTiet);
        return sanPhamChiTietMapper.toResponse(sanPhamChiTiet);
    }

    @Override
    public SanPhamChiTietResponse updateSanPhamChiTiet(UUID id, SanPhamChiTietRequest request) {
        SanPhamChiTiet existing = findById(id);
        if (existing == null) {
            throw new RuntimeException("Id sản phẩm chi tiết '" + id + "' không tồn tại.");
        }
        if (!existing.getMa().equals(request.getMa())) {
            if (findByMa(request.getMa()) != null) {
                throw new RuntimeException(
                        "Mã sản phẩm chi tiết '" + request.getMa() + "' đã tồn tại!");
            }
        }

        sanPhamChiTietMapper.toUpdate(existing, request);
        SanPhamChiTiet updated = sanPhamChiTietRepository.save(existing);
        return sanPhamChiTietMapper.toResponse(updated);

    }

    @Override
    public SanPhamChiTietResponse deleteSanPhamChiTiet(UUID id) {
        SanPhamChiTiet existing = findById(id);
        if (existing == null) {
            throw new RuntimeException("Mã sản phẩm chi tiết '" + id + "' không tồn tại.");
        }
        SanPhamChiTietResponse sanPhamChiTietResponse = sanPhamChiTietMapper.toResponse(existing);
        sanPhamChiTietRepository.delete(existing);
        return sanPhamChiTietResponse;
    }

}
