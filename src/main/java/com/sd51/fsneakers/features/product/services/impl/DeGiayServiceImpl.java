package com.sd51.fsneakers.features.product.services.impl;

import java.util.List;

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
    public List<DeGiay> getAllDeGiay() {
        return deGiayRepository.findAll();
    }

    @Override
    public DeGiay findByMa(String maDeGiay) {
        return deGiayRepository.findByMa(maDeGiay);
    }

    @Override
    public DeGiay createDeGiay(DeGiay deGiay) {
        if (deGiayRepository.findByMa(deGiay.getMa()) != null) {
            throw new RuntimeException("Mã đế giày '" + deGiay.getMa() + "' đã tồn tại!");
        }

        return deGiayRepository.save(deGiay);
    }

    @Override
    public DeGiay updateDeGiay(String ma, DeGiay deGiayUpdate) {
        DeGiay existing = findByMa(ma);
        if (existing == null) {
            throw new RuntimeException("Mã đế giày '" + ma + "' không tồn tại!");
        }
        if (!deGiayUpdate.getMa().equals(ma)) {
            if (findByMa(deGiayUpdate.getMa()) != null) {
                throw new RuntimeException("Mã đế giày '" + deGiayUpdate.getMa() + "' đã tồn tại!");
            } else {

            }
        }

        // Cập nhật các thuộc tính của existing với giá trị từ deGiayUpdate
        existing.setMa(deGiayUpdate.getMa());
        existing.setTen(deGiayUpdate.getTen());
        existing.setTrangThai(deGiayUpdate.getTrangThai());

        return deGiayRepository.save(existing);
    }

    @Override
    public DeGiay deleteDeGiay(String maDeGiay) {
        DeGiay existing = findByMa(maDeGiay);
        if (existing == null) {
            throw new RuntimeException("Mã đế giày '" + maDeGiay + "' không tồn tại!");
        }
        deGiayRepository.delete(existing);
        return existing;
    }

}
