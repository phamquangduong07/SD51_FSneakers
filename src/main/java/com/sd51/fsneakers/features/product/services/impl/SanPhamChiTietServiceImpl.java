package com.sd51.fsneakers.features.product.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;


import com.sd51.fsneakers.features.product.entity.SanPhamChiTiet;
import com.sd51.fsneakers.features.product.repositories.SanPhamChiTietRepository;
import com.sd51.fsneakers.features.product.services.SanPhamChiTietService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SanPhamChiTietServiceImpl implements SanPhamChiTietService {

    SanPhamChiTietRepository sanPhamChiTietRepository;

    @Override
    public List<SanPhamChiTiet> getAllSanPhamChiTiet() {
        return sanPhamChiTietRepository.findAll();
    }

    @Override
    public SanPhamChiTiet findByMa(String maSanPhamChiTiet) {
        return sanPhamChiTietRepository.findByMa(maSanPhamChiTiet);
    }

    @Override
    public SanPhamChiTiet createSanPhamChiTiet(SanPhamChiTiet sanPhamChiTiet) {
        if (sanPhamChiTietRepository.findByMa(sanPhamChiTiet.getMa()) != null) {
            throw new IllegalArgumentException("Mã sản phẩm chi tiết '" + sanPhamChiTiet.getMa() + "' đã tồn tại.");
        }

        return sanPhamChiTietRepository.save(sanPhamChiTiet);
    }

    @Override
    public SanPhamChiTiet updateSanPhamChiTiet(String ma, SanPhamChiTiet sanPhamChiTietUpdate) {
        SanPhamChiTiet existing = findByMa(ma);
        if (existing == null) {
            throw new IllegalArgumentException("Mã sản phẩm chi tiết '" + ma + "' không tồn tại.");
        }
        if (!sanPhamChiTietUpdate.getMa().equals(ma)) {
            if (findByMa(sanPhamChiTietUpdate.getMa()) != null) {
                throw new IllegalArgumentException(
                        "Mã sản phẩm chi tiết '" + sanPhamChiTietUpdate.getMa() + "' đã tồn tại!");
            }
        }
        // Cập nhật các thuộc tính của existing với giá trị từ sanPhamChiTietUpdate
        existing.setMa(sanPhamChiTietUpdate.getMa());
        existing.setMaQr(sanPhamChiTietUpdate.getMaQr());
        existing.setGiaBan(sanPhamChiTietUpdate.getGiaBan());
        existing.setGiaNhap(sanPhamChiTietUpdate.getGiaNhap());
        existing.setSoLuong(sanPhamChiTietUpdate.getSoLuong());
        existing.setMoTa(sanPhamChiTietUpdate.getMoTa());
        existing.setTrangThai(sanPhamChiTietUpdate.getTrangThai());
        existing.setNguoiTao(sanPhamChiTietUpdate.getNguoiTao());
        existing.setNguoiSua(sanPhamChiTietUpdate.getNguoiSua());
        existing.setChatLieu(sanPhamChiTietUpdate.getChatLieu());
        existing.setDanhMuc(sanPhamChiTietUpdate.getDanhMuc());
        existing.setDeGiay(sanPhamChiTietUpdate.getDeGiay());
        existing.setHangGiay(sanPhamChiTietUpdate.getHangGiay());
        existing.setKichThuoc(sanPhamChiTietUpdate.getKichThuoc());
        existing.setMauSac(sanPhamChiTietUpdate.getMauSac());
        existing.setSanPham(sanPhamChiTietUpdate.getSanPham());
        return sanPhamChiTietRepository.save(existing);
    }

    @Override
    public SanPhamChiTiet deleteSanPhamChiTiet(String maSanPhamChiTiet) {
        SanPhamChiTiet existing = findByMa(maSanPhamChiTiet);
        if (existing == null) {
            throw new IllegalArgumentException("Mã sản phẩm chi tiết '" + maSanPhamChiTiet + "' không tồn tại.");
        }
        sanPhamChiTietRepository.delete(existing);
        return existing;
    }

}
