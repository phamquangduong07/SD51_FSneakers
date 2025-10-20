package com.sd51.fsneakers.features.product.services.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sd51.fsneakers.features.product.entity.DanhMuc;
import com.sd51.fsneakers.features.product.repositories.DanhMucRepository;
import com.sd51.fsneakers.features.product.services.DanhMucService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DanhMucServiceImpl implements DanhMucService {

    DanhMucRepository danhMucRepository;

    @Override
    public DanhMuc createDanhMuc(DanhMuc danhMuc) {
        if (findByMa(danhMuc.getMa()) != null) {
            throw new RuntimeException("Mã danh mục '" + danhMuc.getMa() + "' đã tồn tại!");
        }

        return danhMucRepository.save(danhMuc);
    }

    @Override
    public DanhMuc findByMa(String ma) {
        return danhMucRepository.findByMa(ma);
    }

    @Override
    public DanhMuc updateDanhMucByMa(String ma, DanhMuc danhMucUpdate) {
        DanhMuc existing = findByMa(ma);
        if (existing == null) {
            throw new RuntimeException("Mã danh mục '" + ma + "' không tồn tại!");
        }
        if (!danhMucUpdate.getMa().equals(ma)) {
            if (findByMa(danhMucUpdate.getMa()) != null) {
                throw new RuntimeException("Mã danh mục '" + danhMucUpdate.getMa() + "' đã tồn tại!");
            } else {

            }
        }
        // Cập nhật các thuộc tính của existing với giá trị từ danhMucUpdate
        existing.setMa(danhMucUpdate.getMa());
        existing.setTen(danhMucUpdate.getTen());
        existing.setTrangThai(danhMucUpdate.getTrangThai());
        return danhMucRepository.save(existing);
    }

    @Override
    public List<DanhMuc> getAllDanhMuc() {
        return danhMucRepository.findAll();
    }

    @Override
    public DanhMuc deleteDanhMuc(String ma) {
        DanhMuc existing = findByMa(ma);
        if (existing == null) {
            throw new RuntimeException("Mã danh mục '" + ma + "' không tồn tại!");
        }
        danhMucRepository.delete(existing);
        return existing;
    }

    @Override
    public Page<DanhMuc> getAllDanhMucPage(Pageable pageable) {
        return danhMucRepository.getAllPage(pageable);
    }

    @Override
    public Page<DanhMuc> searchDanhMuc(String keyword, Integer trangThai, Pageable pageable) {
        return danhMucRepository.searchDanhMuc(keyword, trangThai, pageable);
    }

}
