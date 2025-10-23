package com.sd51.fsneakers.features.product.services.impl;

import java.util.List;
import java.util.UUID;

import com.sd51.fsneakers.features.mapper.KichThuocMapper;
import com.sd51.fsneakers.features.product.dto.request.KichThuocRequest;
import com.sd51.fsneakers.features.product.dto.response.KichThuocResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sd51.fsneakers.features.product.entity.KichThuoc;
import com.sd51.fsneakers.features.product.repositories.KichThuocRepository;
import com.sd51.fsneakers.features.product.services.KichThuocService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class KichThuocServiceImpl implements KichThuocService {

    KichThuocRepository kichThuocRepository;

    KichThuocMapper kichThuocMapper;

    @Override
    public List<KichThuocResponse> getAllKichThuoc() {
        return kichThuocRepository.findAll().stream().map(kichThuocMapper::toResponse).toList();
    }

    @Override
    public Page<KichThuocResponse> getAllKichThuocPage(Pageable pageable) {
        return kichThuocRepository.getAllPage(pageable).map(kichThuocMapper::toResponse);
    }

    @Override
    public Page<KichThuocResponse> searchKichThuoc(String keyword, Integer trangThai, Pageable pageable) {
        return kichThuocRepository.searchKichThuoc(keyword, trangThai, pageable).map(kichThuocMapper::toResponse);
    }

    @Override
    public KichThuoc findByMa(String maKichThuoc) {
        return kichThuocRepository.findByMa(maKichThuoc);
    }

    @Override
    public KichThuoc findById(UUID id) {
        return kichThuocRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy dữ liệu với id = " + id));
    }

    @Override
    public KichThuocResponse createKichThuoc(KichThuocRequest request) {
        if (kichThuocRepository.findByMa(request.getMa()) != null) {
            throw new RuntimeException("Mã kích thước '" + request.getMa() + "' đã tồn tại.");

        }
        KichThuoc kichThuoc = kichThuocMapper.toEntity(request);
        kichThuocRepository.save(kichThuoc);
        return kichThuocMapper.toResponse(kichThuoc);
    }

    @Override
    public KichThuocResponse updateKichThuoc(UUID id, KichThuocRequest request) {
        KichThuoc existing = findById(id);
        if (existing == null) {
            throw new RuntimeException("Id kích thước '" + id + "' không tồn tại.");
        }
        if (!request.getMa().equals(id)) {
            if (findByMa(request.getMa()) != null) {
                throw new RuntimeException("Mã kích thước '" + request.getMa() + "' đã tồn tại!");
            }
        }
        // Cập nhật các thuộc tính của existing với giá trị từ kichThuocUpdate
        kichThuocMapper.toUpdate(existing, request);
        KichThuoc update = kichThuocRepository.save(existing);
        return kichThuocMapper.toResponse(update);

    }

    @Override
    public KichThuocResponse deleteKichThuoc(UUID id) {
        KichThuoc existing = findById(id);
        if (existing == null) {
            throw new RuntimeException("Mã kích thước '" + id + "' không tồn tại.");
        }
        KichThuocResponse kichThuocResponse = kichThuocMapper.toResponse(existing);
        kichThuocRepository.delete(existing);
        return kichThuocResponse;
    }
}