package com.sd51.fsneakers.features.product.services.impl;

import java.util.List;
import java.util.UUID;

import com.sd51.fsneakers.features.product.mapper.HangGiayMapper;
import com.sd51.fsneakers.features.product.dto.request.HangGiayRequest;
import com.sd51.fsneakers.features.product.dto.response.HangGiayResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sd51.fsneakers.features.product.entity.HangGiay;
import com.sd51.fsneakers.features.product.repositories.HangGiayRepository;
import com.sd51.fsneakers.features.product.services.HangGiayService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HangGiayServiceImpl implements HangGiayService {

    HangGiayRepository hangGiayRepository;
    HangGiayMapper hangGiayMapper;

    @Override
    public List<HangGiayResponse> getAllHangGiay() {
        return hangGiayRepository.findAll().stream().map(hangGiayMapper::toResponse).toList();
    }

    @Override
    public Page<HangGiayResponse> getAllHangGiayPage(Pageable pageable) {
        return hangGiayRepository.getAllPage(pageable).map(hangGiayMapper::toResponse);
    }

    @Override
    public Page<HangGiayResponse> searchHangGiay(String keyword, Integer trangThai, Pageable pageable) {
        return hangGiayRepository.searchHangGiay(keyword, trangThai, pageable).map(hangGiayMapper::toResponse);
    }

    @Override
    public HangGiay findByMa(String ma) {
        return hangGiayRepository.findByMa(ma);
    }

    @Override
    public HangGiay findById(UUID id) {
        return hangGiayRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy dữ liệu với id = " + id));
    }

    @Override
    public HangGiayResponse createHangGiay(HangGiayRequest request) {
        if (findByMa(request.getMa()) != null) {
            throw new RuntimeException("Mã hãng giày '" + request.getMa() + "' đã tồn tại!");
        }
        HangGiay hangGiay = hangGiayMapper.toEntity(request);
        hangGiayRepository.save(hangGiay);
        return hangGiayMapper.toResponse(hangGiay);
    }

    @Override
    public HangGiayResponse updateHangGiay(UUID id, HangGiayRequest request) {
        HangGiay existing = findById(id);
        if (existing == null) {
            throw new RuntimeException("Id hãng giày '" + id + "' không tồn tại!");

        }
        if (!existing.getMa().equals(request.getMa())) {
            if (findByMa(request.getMa()) != null) {
                throw new RuntimeException("Mã hãng giày '" + request.getMa() + "' đã tồn tại khác !");
            } else {

            }
        }
        // Cập nhật các thuộc tính của existing với giá trị từ hangGiayUpdate
        hangGiayMapper.toUpdate(existing, request);
        HangGiay update =  hangGiayRepository.save(existing);
        return hangGiayMapper.toResponse(update);
    }

    @Override
    public HangGiayResponse deleteHangGiay(UUID id) {
        HangGiay existing = findById(id);
        if (existing == null) {
            throw new RuntimeException("Mã hãng giày '" + id + "' không tồn tại!");
        }
        HangGiayResponse hangGiayResponse = hangGiayMapper.toResponse(existing);
        hangGiayRepository.delete(existing);
        return hangGiayResponse;
    }
}
