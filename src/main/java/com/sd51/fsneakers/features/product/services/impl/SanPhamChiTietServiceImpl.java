package com.sd51.fsneakers.features.product.services.impl;

import java.util.List;

import com.sd51.fsneakers.features.mapper.SanPhamChiTietMapper;
import com.sd51.fsneakers.features.product.dto.request.SanPhamChiTietRequest;
import com.sd51.fsneakers.features.product.dto.response.SanPhamChiTietResponse;
import com.sd51.fsneakers.features.product.repositories.ChatLieuRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sd51.fsneakers.features.product.entity.SanPhamChiTiet;
import com.sd51.fsneakers.features.product.repositories.SanPhamChiTietRepository;
import com.sd51.fsneakers.features.product.services.SanPhamChiTietService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class SanPhamChiTietServiceImpl implements SanPhamChiTietService {

    SanPhamChiTietRepository sanPhamChiTietRepository;

    ChatLieuRepository chatLieuRepository;


    @Override
    public List<SanPhamChiTietResponse> getAllSanPhamChiTiet() {
        return sanPhamChiTietRepository.findAll().stream().map(SanPhamChiTietMapper::toResponse).toList();
    }

    @Override
    public Page<SanPhamChiTietResponse> getAllSanPhamChiTietPage(Pageable pageable) {
        return sanPhamChiTietRepository.getAllPage(pageable).map(SanPhamChiTietMapper::toResponse);
    }

    @Override
    public Page<SanPhamChiTietResponse> searchSanPhamChiTiet(String keyword, Integer trangThai, Pageable pageable) {
        return sanPhamChiTietRepository.searchSanPhamChiTiet(keyword, trangThai, pageable)
                .map(SanPhamChiTietMapper::toResponse);
    }

    @Override
    public SanPhamChiTiet findByMa(String maSanPhamChiTiet) {
        return sanPhamChiTietRepository.findByMa(maSanPhamChiTiet);
    }

    @Override
    public SanPhamChiTietResponse createSanPhamChiTiet(SanPhamChiTietRequest request) {
        if (sanPhamChiTietRepository.findByMa(request.getMa()) != null) {
            throw new RuntimeException("Mã sản phẩm chi tiết '" + request.getMa() + "' đã tồn tại.");
        }


        SanPhamChiTiet sanPhamChiTiet = SanPhamChiTietMapper.toEntity(request);
        sanPhamChiTietRepository.save(sanPhamChiTiet);
        return SanPhamChiTietMapper.toResponse(sanPhamChiTiet);
    }

    @Override
    public SanPhamChiTietResponse updateSanPhamChiTiet(String ma, SanPhamChiTietRequest request) {
        SanPhamChiTiet existing = findByMa(ma);
        if (existing == null) {
            throw new RuntimeException("Mã sản phẩm chi tiết '" + ma + "' không tồn tại.");
        }
        if (!request.getMa().equals(ma)) {
            if (findByMa(request.getMa()) != null) {
                throw new RuntimeException(
                        "Mã sản phẩm chi tiết '" + request.getMa() + "' đã tồn tại!");
            }
        }

        SanPhamChiTietMapper.toUpdate(existing, request);
        SanPhamChiTiet updated = sanPhamChiTietRepository.save(existing);
        return SanPhamChiTietMapper.toResponse(updated);

        // Cập nhật các thuộc tính của existing với giá trị từ sanPhamChiTietUpdate
//        existing.setMa(sanPhamChiTietUpdate.getMa());
//        existing.setMaQr(sanPhamChiTietUpdate.getMaQr());
//        existing.setGiaBan(sanPhamChiTietUpdate.getGiaBan());
//        existing.setGiaNhap(sanPhamChiTietUpdate.getGiaNhap());
//        existing.setSoLuong(sanPhamChiTietUpdate.getSoLuong());
//        existing.setMoTa(sanPhamChiTietUpdate.getMoTa());
//        existing.setTrangThai(sanPhamChiTietUpdate.getTrangThai());
//        existing.setNguoiTao(sanPhamChiTietUpdate.getNguoiTao());
//        existing.setNguoiSua(sanPhamChiTietUpdate.getNguoiSua());
//        existing.setChatLieu(sanPhamChiTietUpdate.getChatLieu());
//        existing.setDanhMuc(sanPhamChiTietUpdate.getDanhMuc());
//        existing.setDeGiay(sanPhamChiTietUpdate.getDeGiay());
//        existing.setHangGiay(sanPhamChiTietUpdate.getHangGiay());
//        existing.setKichThuoc(sanPhamChiTietUpdate.getKichThuoc());
//        existing.setMauSac(sanPhamChiTietUpdate.getMauSac());
//        existing.setSanPham(sanPhamChiTietUpdate.getSanPham());

    }

    @Override
    public void deleteSanPhamChiTiet(String maSanPhamChiTiet) {
        SanPhamChiTiet existing = findByMa(maSanPhamChiTiet);
        if (existing == null) {
            throw new RuntimeException("Mã sản phẩm chi tiết '" + maSanPhamChiTiet + "' không tồn tại.");
        }
        sanPhamChiTietRepository.delete(existing);
    }


}
