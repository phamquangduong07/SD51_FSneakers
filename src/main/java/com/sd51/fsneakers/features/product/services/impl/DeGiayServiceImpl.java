package com.sd51.fsneakers.features.product.services.impl;

import java.util.List;

import com.sd51.fsneakers.features.mapper.DeGiayMapper;
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

    @Override
    public List<DeGiayResponse> getAllDeGiay() {
        return deGiayRepository.findAll().stream().map(DeGiayMapper::toResponse).toList();
    }

    @Override
    public Page<DeGiayResponse> getAllDeGiayPage(Pageable pageable) {
        return deGiayRepository.getAllPage(pageable).map(DeGiayMapper::toResponse);
    }

    @Override
    public Page<DeGiayResponse> searchDeGiay(String keyword, Integer trangThai, Pageable pageable) {
        return deGiayRepository.searchDeGiay(keyword, trangThai, pageable).map(DeGiayMapper::toResponse);
    }

    @Override
    public DeGiay findByMa(String maDeGiay) {
        return deGiayRepository.findByMa(maDeGiay);
    }

    @Override
    public DeGiayResponse createDeGiay(DeGiayRequest request) {
        if (deGiayRepository.findByMa(request.getMa()) != null) {
            throw new RuntimeException("Mã đế giày '" + request.getMa() + "' đã tồn tại!");
        }
        DeGiay deGiay = DeGiayMapper.toEntity(request);
        deGiayRepository.save(deGiay);
        return DeGiayMapper.toResponse(deGiay);
    }

    @Override
    public DeGiayResponse updateDeGiay(String ma, DeGiayRequest request) {
        DeGiay existing = findByMa(ma);
        if (existing == null) {
            throw new RuntimeException("Mã đế giày '" + ma + "' không tồn tại!");
        }
        if (!request.getMa().equals(ma)) {
            if (findByMa(request.getMa()) != null) {
                throw new RuntimeException("Mã đế giày '" + request.getMa() + "' đã tồn tại!");
            } else {

            }
        }

        // Cập nhật các thuộc tính của existing với giá trị từ deGiayUpdate
        DeGiayMapper.toUpdate(existing, request);
        DeGiay update = deGiayRepository.save(existing);

        return DeGiayMapper.toResponse(update);
    }

    @Override
    public void deleteDeGiay(String maDeGiay) {
        DeGiay existing = findByMa(maDeGiay);
        if (existing == null) {
            throw new RuntimeException("Mã đế giày '" + maDeGiay + "' không tồn tại!");
        }
        deGiayRepository.delete(existing);
    }
}
