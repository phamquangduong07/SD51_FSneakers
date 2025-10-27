package com.sd51.fsneakers.features.product.services.impl;

import java.util.List;
import java.util.UUID;

import com.sd51.fsneakers.features.product.mapper.DanhMucMapper;
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
    DanhMucMapper danhMucMapper;

    @Override
    public List<DanhMucResponse> getAllDanhMuc() {
        return danhMucRepository.findAll().stream().map(danhMucMapper::toResponse).toList();
    }

    @Override
    public Page<DanhMucResponse> getAllDanhMucPage(Pageable pageable) {
        return danhMucRepository.getAllPage(pageable).map(danhMucMapper::toResponse);
    }

    @Override
    public Page<DanhMucResponse> searchDanhMuc(String keyword, Integer trangThai, Pageable pageable) {
        return danhMucRepository.searchDanhMuc(keyword, trangThai, pageable).map(danhMucMapper::toResponse);
    }


    @Override
    public DanhMuc findByMa(String ma) {
        return danhMucRepository.findByMa(ma);
    }

    @Override
    public DanhMuc findById(UUID id) {
        return danhMucRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy dữ liệu với id = " + id));
    }

    @Override
    public DanhMucResponse createDanhMuc(DanhMucRequest request) {
        if (findByMa(request.getMa()) != null) {
            throw new RuntimeException("Mã danh mục '" + request.getMa() + "' đã tồn tại!");
        }
        DanhMuc danhMuc = danhMucMapper.toEntity(request);
        danhMucRepository.save(danhMuc);
        return danhMucMapper.toResponse(danhMuc);
    }

    @Override
    public DanhMucResponse updateDanhMucByMa(UUID ma, DanhMucRequest request) {
        DanhMuc existing = findById(ma);
        if (existing == null) {
            throw new RuntimeException("Id danh mục '" + ma + "' không tồn tại!");
        }
        if (!existing.getMa().equals(request.getMa())) {
            if (findByMa(request.getMa()) != null) {
                throw new RuntimeException("Mã danh mục '" + request.getMa() + "' đã tồn tại!");
            } else {

            }
        }
        // Cập nhật các field (tự động với MapStruct)
        danhMucMapper.toUpdate(existing, request);

        DanhMuc update = danhMucRepository.save(existing);

        return danhMucMapper.toResponse(update);
    }


    @Override
    public DanhMucResponse deleteDanhMuc(UUID id) {
        DanhMuc existing = findById(id);
        if (existing == null) {
            throw new RuntimeException("Id danh mục '" + id + "' không tồn tại!");
        }
        DanhMucResponse delete = danhMucMapper.toResponse(existing);
        danhMucRepository.delete(existing);
        return delete;
    }
}
