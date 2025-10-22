package com.sd51.fsneakers.features.product.services.impl;

import java.util.List;

import com.sd51.fsneakers.features.mapper.HangGiayMapper;
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

    @Override
    public List<HangGiayResponse> getAllHangGiay() {
        return hangGiayRepository.findAll().stream().map(HangGiayMapper::toResponse).toList();
    }

    @Override
    public Page<HangGiayResponse> getAllHangGiayPage(Pageable pageable) {
        return hangGiayRepository.getAllPage(pageable).map(HangGiayMapper::toResponse);
    }

    @Override
    public Page<HangGiayResponse> searchHangGiay(String keyword, Integer trangThai, Pageable pageable) {
        return hangGiayRepository.searchHangGiay(keyword, trangThai, pageable).map(HangGiayMapper::toResponse);
    }

    @Override
    public HangGiay findByMa(String ma) {
        return hangGiayRepository.findByMa(ma);
    }

    @Override
    public HangGiayResponse createHangGiay(HangGiayRequest request) {
        if (findByMa(request.getMa()) != null) {
            throw new RuntimeException("Mã hãng giày '" + request.getMa() + "' đã tồn tại!");
        }
        HangGiay hangGiay = HangGiayMapper.toEntity(request);
        hangGiayRepository.save(hangGiay);
        return HangGiayMapper.toResponse(hangGiay);
    }

    @Override
    public HangGiayResponse updateHangGiay(String ma, HangGiayRequest request) {
        HangGiay existing = findByMa(ma);
        if (existing == null) {
            throw new RuntimeException("Mã hãng giày '" + ma + "' không tồn tại!");

        }
        if (!request.getMa().equals(ma)) {
            if (findByMa(request.getMa()) != null) {
                throw new RuntimeException("Mã hãng giày '" + request.getMa() + "' đã tồn tại khác !");
            } else {

            }
        }
        // Cập nhật các thuộc tính của existing với giá trị từ hangGiayUpdate
        HangGiayMapper.toUpdate(existing, request);
        HangGiay update =  hangGiayRepository.save(existing);
        return HangGiayMapper.toResponse(update);
    }

    @Override
    public void deleteHangGiay(String ma) {
        HangGiay existing = findByMa(ma);
        if (existing == null) {
            throw new RuntimeException("Mã hãng giày '" + ma + "' không tồn tại!");
        }
        hangGiayRepository.delete(existing);
    }


}
