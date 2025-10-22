package com.sd51.fsneakers.features.product.services.impl;

import java.util.List;

import com.sd51.fsneakers.features.mapper.DanhMucMapper;
import com.sd51.fsneakers.features.product.dto.request.DanhMucRequest;
import com.sd51.fsneakers.features.product.dto.response.DanhMucResponse;
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
    public List<DanhMucResponse> getAllDanhMuc() {
        return danhMucRepository.findAll().stream().map(DanhMucMapper::toResponse).toList();
    }

    @Override
    public Page<DanhMucResponse> getAllDanhMucPage(Pageable pageable) {
        return danhMucRepository.getAllPage(pageable).map(DanhMucMapper::toResponse);
    }

    @Override
    public Page<DanhMucResponse> searchDanhMuc(String keyword, Integer trangThai, Pageable pageable) {
        return danhMucRepository.searchDanhMuc(keyword, trangThai, pageable).map(DanhMucMapper::toResponse);
    }


    @Override
    public DanhMuc findByMa(String ma) {
        return danhMucRepository.findByMa(ma);
    }

    @Override
    public DanhMucResponse createDanhMuc(DanhMucRequest request) {
        if (findByMa(request.getMa()) != null) {
            throw new RuntimeException("Mã danh mục '" + request.getMa() + "' đã tồn tại!");
        }
        DanhMuc danhMuc = DanhMucMapper.toEntity(request);
        danhMucRepository.save(danhMuc);
        return DanhMucMapper.toResponse(danhMuc);
    }

    @Override
    public DanhMucResponse updateDanhMucByMa(String ma, DanhMucRequest request) {
        DanhMuc existing = findByMa(ma);
        if (existing == null) {
            throw new RuntimeException("Mã danh mục '" + ma + "' không tồn tại!");
        }
        if (!request.getMa().equals(ma)) {
            if (findByMa(request.getMa()) != null) {
                throw new RuntimeException("Mã danh mục '" + request.getMa() + "' đã tồn tại!");
            } else {

            }
        }
        // Cập nhật các field (tự động với MapStruct)
        DanhMucMapper.toUpdate(existing, request);

        DanhMuc update = danhMucRepository.save(existing);

        return DanhMucMapper.toResponse(update);
    }


    @Override
    public void deleteDanhMuc(String ma) {
        DanhMuc existing = findByMa(ma);
        if (existing == null) {
            throw new RuntimeException("Mã danh mục '" + ma + "' không tồn tại!");
        }
        danhMucRepository.delete(existing);
    }
}
