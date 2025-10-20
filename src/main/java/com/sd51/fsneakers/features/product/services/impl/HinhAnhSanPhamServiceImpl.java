package com.sd51.fsneakers.features.product.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sd51.fsneakers.features.product.entity.HinhAnhSanPham;
import com.sd51.fsneakers.features.product.repositories.HinhAnhSanPhamRepository;
import com.sd51.fsneakers.features.product.services.HinhAnhSanPhamService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HinhAnhSanPhamServiceImpl implements HinhAnhSanPhamService {

    HinhAnhSanPhamRepository hinhAnhSanPhamRepository;

    @Override
    public List<HinhAnhSanPham> getAllHinhAnhBySanPham() {
        return hinhAnhSanPhamRepository.findAll();
    }

    @Override
    public HinhAnhSanPham findByMa(String ma) {
        return hinhAnhSanPhamRepository.findByMa(ma);
    }

    @Override
    public HinhAnhSanPham createHinhAnhSanPham(HinhAnhSanPham hinhAnhSanPham) {
        if (hinhAnhSanPhamRepository.findByMa(hinhAnhSanPham.getMa()) != null) {
            throw new IllegalArgumentException("Mã hình ảnh sản phẩm '" + hinhAnhSanPham.getMa() + "' đã tồn tại.");
        }

        return hinhAnhSanPhamRepository.save(hinhAnhSanPham);
    }

    @Override
    public HinhAnhSanPham updateHinhAnhSanPham(String ma, HinhAnhSanPham hinhAnhSanPhamUpdate) {
         HinhAnhSanPham existing = findByMa(ma);
        if (existing == null) {
            throw new RuntimeException("Mã hình ảnh sản phẩm '" + ma + "' không tồn tại!");

        }
        if (!hinhAnhSanPhamUpdate.getMa().equals(ma)) {
            if (findByMa(hinhAnhSanPhamUpdate.getMa()) != null) {
                throw new RuntimeException("Mã hình ảnh sản phẩm '" + hinhAnhSanPhamUpdate.getMa() + "' đã tồn tại!");
            } else {

            }
        }
        // Cập nhật các thuộc tính của existing với giá trị từ hinhAnhSanPhamUpdate
        existing.setMa(hinhAnhSanPhamUpdate.getMa());
        existing.setTen(hinhAnhSanPhamUpdate.getTen());
        existing.setUrl(hinhAnhSanPhamUpdate.getUrl());
        existing.setTrangThai(hinhAnhSanPhamUpdate.getTrangThai());
        existing.setChiTietSanPham(hinhAnhSanPhamUpdate.getChiTietSanPham());
        return hinhAnhSanPhamRepository.save(existing);
    }

    @Override
    public HinhAnhSanPham deleteHinhAnhSanPham(String ma) {
        HinhAnhSanPham existing = findByMa(ma);
        if (existing == null) {
            throw new RuntimeException("Mã hình ảnh sản phẩm '" + ma + "' không tồn tại!");
        }
        hinhAnhSanPhamRepository.delete(existing);
        return existing;
    }

}
