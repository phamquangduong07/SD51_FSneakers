package com.sd51.fsneakers.features.product.services.impl;

import java.util.List;

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
    public List<SanPham> getAllSanPham() {
        return sanPhamRepository.findAll();
    }

    @Override
    public SanPham findByMa(String maSanPham) {
        return sanPhamRepository.findByMa(maSanPham);
    }

    @Override
    public SanPham createSanPham(SanPham sanPham) {
        if (sanPhamRepository.findByMa(sanPham.getMa()) != null) {
            throw new IllegalArgumentException("Mã sản phẩm '" + sanPham.getMa() + "' đã tồn tại");
        }

        return sanPhamRepository.save(sanPham);
    }

    @Override
    public SanPham updateSanPham(String ma, SanPham updateSanPham) {
        SanPham existing = findByMa(ma);
        if (existing == null) {
            throw new IllegalArgumentException("Mã sản phẩm '" + ma + "' không tồn tại.");
        }
        if (!updateSanPham.getMa().equals(ma)) {
            if (findByMa(updateSanPham.getMa()) != null) {
                throw new IllegalArgumentException("Mã sản phẩm '" + updateSanPham.getMa() + "' đã tồn tại!");
            }
        }
        // Cập nhật các thuộc tính của existing với giá trị từ updateSanPham
        existing.setMa(updateSanPham.getMa());
        existing.setTen(updateSanPham.getTen());
        existing.setMoTa(updateSanPham.getMoTa());
        existing.setTrangThai(updateSanPham.getTrangThai());
        return sanPhamRepository.save(existing);
    }

    @Override
    public SanPham deleteSanPham(String maSanPham) {
        SanPham existing = findByMa(maSanPham);
        if (existing == null) {
            throw new IllegalArgumentException("Mã sản phẩm '" + maSanPham + "' không tồn tại.");
        }

        sanPhamRepository.delete(existing);
        return existing;
    }

    @Override
    public Page<SanPham> getAllSanPhamPage(Pageable pageable) {
        return sanPhamRepository.getAllPage(pageable);
    }

    @Override
    public Page<SanPham> searchSanPham(String keyword, Integer trangThai, Pageable pageable) {
        return sanPhamRepository.searchSanPham(keyword, trangThai, pageable);
    }

}
