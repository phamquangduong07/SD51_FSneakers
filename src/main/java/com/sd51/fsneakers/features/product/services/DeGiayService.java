package com.sd51.fsneakers.features.product.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sd51.fsneakers.features.product.entity.DeGiay;

public interface DeGiayService {

    List<DeGiay> getAllDeGiay();

    DeGiay findByMa(String maDeGiay);

    DeGiay createDeGiay(DeGiay deGiay);

    DeGiay updateDeGiay(String ma, DeGiay deGiayUpdate);

    DeGiay deleteDeGiay(String maDeGiay);

    Page<DeGiay> getAllDeGiayPage(Pageable pageable);

    Page<DeGiay> searchDeGiay(String keyword, Integer trangThai, Pageable pageable);
}
