package com.sd51.fsneakers.features.product.services.impl;

import java.util.List;

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
    public List<KichThuoc> getAllKichThuoc() {
        return kichThuocRepository.findAll();
    }

    @Override
    public KichThuoc findByMa(String maKichThuoc) {
        return kichThuocRepository.findByMa(maKichThuoc);
    }

    @Override
    public KichThuoc createKichThuoc(KichThuoc kichThuoc) {
        if (kichThuocRepository.findByMa(kichThuoc.getMa()) != null) {
            throw new RuntimeException("Mã kích thước '" + kichThuoc.getMa() + "' đã tồn tại.");
            
        }
        return kichThuocRepository.save(kichThuoc);
    }

    @Override
    public KichThuoc updateKichThuoc(String ma, KichThuoc kichThuocUpdate) {
        KichThuoc existing = findByMa(ma);
        if (existing == null) {
            throw new IllegalArgumentException("Mã kích thước '" + ma + "' không tồn tại.");
        }
        if (!kichThuocUpdate.getMa().equals(ma)) {
            if (findByMa(kichThuocUpdate.getMa()) != null) {
                throw new IllegalArgumentException("Mã kích thước '" + kichThuocUpdate.getMa() + "' đã tồn tại!");
            }
        }
        // Cập nhật các thuộc tính của existing với giá trị từ kichThuocUpdate
        existing.setMa(kichThuocUpdate.getMa());
        existing.setTen(kichThuocUpdate.getTen());
        existing.setTrangThai(kichThuocUpdate.getTrangThai());
        return kichThuocRepository.save(existing);
    }

    @Override
    public KichThuoc deleteKichThuoc(String maKichThuoc) {
        KichThuoc existing = findByMa(maKichThuoc);
        if (existing == null) {
            throw new IllegalArgumentException("Mã kích thước '" + maKichThuoc + "' không tồn tại.");
        }
        kichThuocRepository.delete(existing);
        return existing;
    }

}
