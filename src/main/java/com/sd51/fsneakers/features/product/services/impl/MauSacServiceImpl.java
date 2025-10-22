package com.sd51.fsneakers.features.product.services.impl;

import java.util.List;

import com.sd51.fsneakers.features.mapper.MauSacMapper;
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

    @Override
    public List<MauSacResponse> getAllMauSac() {
        return mauSacRepository.findAll().stream().map(MauSacMapper::toResponse).toList();
    }

    @Override
    public Page<MauSacResponse> getAllMauSacPage(Pageable pageable) {
        return mauSacRepository.getAllPage(pageable).map(MauSacMapper::toResponse);
    }

    @Override
    public Page<MauSacResponse> searchMauSac(String keyword, Integer trangThai, Pageable pageable) {
        return mauSacRepository.searchMauSac(keyword, trangThai, pageable).map(MauSacMapper::toResponse);
    }

    @Override
    public MauSac findByMa(String maMauSac) {
        return mauSacRepository.findByMa(maMauSac);
    }

    @Override
    public MauSacResponse createMauSac(MauSacRequest request) {
        if (mauSacRepository.findByMa(request.getMa()) != null) {
            throw new RuntimeException("Mã màu sắc '" + request.getMa() + "' đã tồn tại.");
        }
        MauSac mauSac = MauSacMapper.toEntity(request);
        mauSacRepository.save(mauSac);
        return MauSacMapper.toResponse(mauSac);
    }

    @Override
    public MauSacResponse updateMauSac(String ma, MauSacRequest request) {
        MauSac existing = findByMa(ma);
        if (existing == null) {
            throw new RuntimeException("Mã màu sắc '" + ma + "' không tồn tại.");
        }
        if (!request.getMa().equals(ma)) {
            if (findByMa(request.getMa()) != null) {
                throw new RuntimeException("Mã màu sắc '" + request.getMa() + "' đã tồn tại!");
            }
        }
        // Cập nhật các thuộc tính của existing với giá trị từ mauSacUpdate
        MauSacMapper.toUpdate(existing, request);
        MauSac update = mauSacRepository.save(existing);
        return MauSacMapper.toResponse(update);
    }

    @Override
    public void deleteMauSac(String maMauSac) {
        MauSac existing = findByMa(maMauSac);
        if (existing == null) {
            throw new RuntimeException("Mã màu sắc '" + maMauSac + "' không tồn tại.");
        }

        mauSacRepository.delete(existing);
    }


}
