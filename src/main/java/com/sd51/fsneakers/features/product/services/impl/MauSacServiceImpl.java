package com.sd51.fsneakers.features.product.services.impl;

import java.util.List;
import java.util.UUID;

import com.sd51.fsneakers.features.product.mapper.MauSacMapper;
import com.sd51.fsneakers.features.product.dto.request.MauSacRequest;
import com.sd51.fsneakers.features.product.dto.response.MauSacResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sd51.fsneakers.features.product.entity.MauSac;
import com.sd51.fsneakers.features.product.repositories.MauSacRepository;
import com.sd51.fsneakers.features.product.services.MauSacService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MauSacServiceImpl implements MauSacService {

    MauSacRepository mauSacRepository;

    MauSacMapper mauSacMapper;

    @Override
    public List<MauSacResponse> getAllMauSac() {
        return mauSacRepository.findAll().stream().map(mauSacMapper::toResponse).toList();
    }

    @Override
    public Page<MauSacResponse> getAllMauSacPage(Pageable pageable) {
        return mauSacRepository.getAllPage(pageable).map(mauSacMapper::toResponse);
    }

    @Override
    public Page<MauSacResponse> searchMauSac(String keyword, Integer trangThai, Pageable pageable) {
        return mauSacRepository.searchMauSac(keyword, trangThai, pageable).map(mauSacMapper::toResponse);
    }

    @Override
    public MauSac findByMa(String maMauSac) {
        return mauSacRepository.findByMa(maMauSac);
    }

    @Override
    public MauSac findById(UUID id) {
        return mauSacRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy dữ liệu với id = " + id));
    }


    @Override
    public MauSacResponse createMauSac(MauSacRequest request) {
        if (mauSacRepository.findByMa(request.getMa()) != null) {
            throw new RuntimeException("Mã màu sắc '" + request.getMa() + "' đã tồn tại.");
        }
        MauSac mauSac = mauSacMapper.toEntity(request);
        mauSacRepository.save(mauSac);
        return mauSacMapper.toResponse(mauSac);
    }

    @Override
    public MauSacResponse updateMauSac(UUID id, MauSacRequest request) {
        MauSac existing = findById(id);
        if (existing == null) {
            throw new RuntimeException("Id màu sắc '" + id + "' không tồn tại.");
        }
        if (!existing.getMa().equals(request.getMa())) {
            if (findByMa(request.getMa()) != null) {
                throw new RuntimeException("Mã màu sắc '" + request.getMa() + "' đã tồn tại!");
            }
        }
        // Cập nhật các thuộc tính của existing với giá trị từ mauSacUpdate
        mauSacMapper.toUpdate(existing, request);
        MauSac update = mauSacRepository.save(existing);
        return mauSacMapper.toResponse(update);
    }

    @Override
    public MauSacResponse deleteMauSac(UUID id) {
        MauSac existing = findById(id);
        if (existing == null) {
            throw new RuntimeException("Id màu sắc '" + id + "' không tồn tại.");
        }
        MauSacResponse mauSacResponse = mauSacMapper.toResponse(existing);
        mauSacRepository.delete(existing);
        return mauSacResponse;
    }
}
