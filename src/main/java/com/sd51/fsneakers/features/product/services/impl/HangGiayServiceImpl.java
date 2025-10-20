package com.sd51.fsneakers.features.product.services.impl;

import java.util.List;

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
    public List<HangGiay> getAllHangGiay() {
        return hangGiayRepository.findAll();
    }

    @Override
    public HangGiay findByMa(String ma) {
        return hangGiayRepository.findByMa(ma);
    }

    @Override
    public HangGiay createHangGiay(HangGiay hangGiay) {
        if (findByMa(hangGiay.getMa()) != null) {
            throw new RuntimeException("Mã hãng giày '" + hangGiay.getMa() + "' đã tồn tại!");
        }

        return hangGiayRepository.save(hangGiay);
    }

    @Override
    public HangGiay updateHangGiay(String ma, HangGiay hangGiayUpdate) {
        HangGiay existing = findByMa(ma);
        if (existing == null) {
            throw new RuntimeException("Mã hãng giày '" + ma + "' không tồn tại!");

        }
        if (!hangGiayUpdate.getMa().equals(ma)) {
            if (findByMa(hangGiayUpdate.getMa()) != null) {
                throw new RuntimeException("Mã hãng giày '" + hangGiayUpdate.getMa() + "' đã tồn tại khác !");
            } else {

            }
        }
        // Cập nhật các thuộc tính của existing với giá trị từ hangGiayUpdate
        existing.setMa(hangGiayUpdate.getMa());
        existing.setTen(hangGiayUpdate.getTen());
        existing.setTrangThai(hangGiayUpdate.getTrangThai());
        return hangGiayRepository.save(existing);
    }

    @Override
    public HangGiay deleteHangGiay(String ma) {
        HangGiay existing = findByMa(ma);
        if (existing == null) {
            throw new RuntimeException("Mã hãng giày '" + ma + "' không tồn tại!");
        }
        hangGiayRepository.delete(existing);
        return existing;
    }

}
