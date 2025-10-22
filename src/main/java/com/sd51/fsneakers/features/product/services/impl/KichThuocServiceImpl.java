package com.sd51.fsneakers.features.product.services.impl;

import java.util.List;

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

    @Override
    public List<KichThuocResponse> getAllKichThuoc() {
        return kichThuocRepository.findAll().stream().map(KichThuocMapper::toResponse).toList();
    }

    @Override
    public Page<KichThuocResponse> getAllKichThuocPage(Pageable pageable) {
        return kichThuocRepository.getAllPage(pageable).map(KichThuocMapper::toResponse);
    }

    @Override
    public Page<KichThuocResponse> searchKichThuoc(String keyword, Integer trangThai, Pageable pageable) {
        return kichThuocRepository.searchKichThuoc(keyword, trangThai, pageable).map(KichThuocMapper::toResponse);
    }

    @Override
    public KichThuoc findByMa(String maKichThuoc) {
        return kichThuocRepository.findByMa(maKichThuoc);
    }

    @Override
    public KichThuocResponse createKichThuoc(KichThuocRequest request) {
        if (kichThuocRepository.findByMa(request.getMa()) != null) {
            throw new RuntimeException("Mã kích thước '" + request.getMa() + "' đã tồn tại.");

        }
        KichThuoc kichThuoc = KichThuocMapper.toEntity(request);
        kichThuocRepository.save(kichThuoc);
        return KichThuocMapper.toResponse(kichThuoc);
    }

    @Override
    public KichThuocResponse updateKichThuoc(String ma, KichThuocRequest request) {
        KichThuoc existing = findByMa(ma);
        if (existing == null) {
            throw new RuntimeException("Mã kích thước '" + ma + "' không tồn tại.");
        }
        if (!request.getMa().equals(ma)) {
            if (findByMa(request.getMa()) != null) {
                throw new RuntimeException("Mã kích thước '" + request.getMa() + "' đã tồn tại!");
            }
        }
        // Cập nhật các thuộc tính của existing với giá trị từ kichThuocUpdate
        KichThuocMapper.toUpdate(existing, request);
        KichThuoc update = kichThuocRepository.save(existing);
        return KichThuocMapper.toResponse(update);

    }

    @Override
    public void deleteKichThuoc(String maKichThuoc) {
        KichThuoc existing = findByMa(maKichThuoc);
        if (existing == null) {
            throw new RuntimeException("Mã kích thước '" + maKichThuoc + "' không tồn tại.");
        }
        kichThuocRepository.delete(existing);
    }


}