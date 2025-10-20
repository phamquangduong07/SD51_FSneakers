package com.sd51.fsneakers.features.product.services.impl;

import java.util.List;

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
    public List<MauSac> getAllMauSac() {
        return mauSacRepository.findAll();
    }

    @Override
    public MauSac findByMa(String maMauSac) {
        return mauSacRepository.findByMa(maMauSac);
    }

    @Override
    public MauSac createMauSac(MauSac mauSac) {
        if (mauSacRepository.findByMa(mauSac.getMa()) != null) {
            throw new IllegalArgumentException("Mã màu sắc '" + mauSac.getMa() + "' đã tồn tại.");
        }
        return mauSacRepository.save(mauSac);
    }

    @Override
    public MauSac updateMauSac(String ma, MauSac mauSacUpdate) {
        MauSac existing = findByMa(ma);
        if (existing == null) {
            throw new IllegalArgumentException("Mã màu sắc '" + ma + "' không tồn tại.");
        }
        if (!mauSacUpdate.getMa().equals(ma)) {
            if (findByMa(mauSacUpdate.getMa()) != null) {
                throw new IllegalArgumentException("Mã màu sắc '" + mauSacUpdate.getMa() + "' đã tồn tại!");
            }
        }
        // Cập nhật các thuộc tính của existing với giá trị từ mauSacUpdate
        existing.setMa(mauSacUpdate.getMa());
        existing.setTen(mauSacUpdate.getTen());
        existing.setTrangThai(mauSacUpdate.getTrangThai());
        return mauSacRepository.save(existing);
    }

    @Override
    public MauSac deleteMauSac(String maMauSac) {
        MauSac existing = findByMa(maMauSac);
        if (existing == null) {
            throw new IllegalArgumentException("Mã màu sắc '" + maMauSac + "' không tồn tại.");
        }
        
        mauSacRepository.delete(existing);
        return existing;
    }

}
