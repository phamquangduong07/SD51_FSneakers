package com.sd51.fsneakers.features.product.services.impl;

import java.util.List;
import java.util.UUID;

import com.sd51.fsneakers.features.product.mapper.DeGiayMapper;
import com.sd51.fsneakers.features.product.dto.request.DeGiayRequest;
import com.sd51.fsneakers.features.product.dto.response.DeGiayResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sd51.fsneakers.features.product.entity.DeGiay;
import com.sd51.fsneakers.features.product.repositories.DeGiayRepository;
import com.sd51.fsneakers.features.product.services.DeGiayService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DeGiayServiceImpl implements DeGiayService {

    DeGiayRepository deGiayRepository;
    DeGiayMapper deGiayMapper;

    @Override
    public List<DeGiayResponse> getAllDeGiay() {
        return deGiayRepository.findAll().stream().map(deGiayMapper::toResponse).toList();
    }

    @Override
    public Page<DeGiayResponse> getAllDeGiayPage(Pageable pageable) {
        return deGiayRepository.getAllPage(pageable).map(deGiayMapper::toResponse);
    }

    @Override
    public Page<DeGiayResponse> searchDeGiay(String keyword, Integer trangThai, Pageable pageable) {
        return deGiayRepository.searchDeGiay(keyword, trangThai, pageable).map(deGiayMapper::toResponse);
    }

    @Override
    public DeGiay findByMa(String maDeGiay) {
        return deGiayRepository.findByMa(maDeGiay);
    }

    @Override
    public DeGiay findById(UUID id) {
        return deGiayRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy dữ liệu với id = " + id));
    }

    @Override
    public DeGiayResponse createDeGiay(DeGiayRequest request) {
        if (deGiayRepository.findByMa(request.getMa()) != null) {
            throw new RuntimeException("Mã đế giày '" + request.getMa() + "' đã tồn tại!");
        }
        DeGiay deGiay = deGiayMapper.toEntity(request);
        deGiayRepository.save(deGiay);
        return deGiayMapper.toResponse(deGiay);
    }

    @Override
    public DeGiayResponse updateDeGiay(UUID id, DeGiayRequest request) {
        DeGiay existing = findById(id);
        if (existing == null) {
            throw new RuntimeException("Id đế giày '" + id + "' không tồn tại!");
        }
        if (!existing.getMa().equals(request.getMa())) {
            if (findByMa(request.getMa()) != null) {
                throw new RuntimeException("Mã đế giày '" + request.getMa() + "' đã tồn tại!");
            } else {

            }
        }

        // Cập nhật các thuộc tính của existing với giá trị từ deGiayUpdate
        deGiayMapper.toUpdate(existing, request);
        DeGiay update = deGiayRepository.save(existing);

        return deGiayMapper.toResponse(update);
    }

    @Override
    public DeGiayResponse deleteDeGiay(UUID id) {
        DeGiay existing = findById(id);
        if (existing == null) {
            throw new RuntimeException("Id đế giày '" + id + "' không tồn tại!");
        }
        DeGiayResponse deGiayResponse = deGiayMapper.toResponse(existing);
        deGiayRepository.delete(existing);
        return deGiayResponse;
    }
}
