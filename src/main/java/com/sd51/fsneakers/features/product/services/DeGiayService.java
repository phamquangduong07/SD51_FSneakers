package com.sd51.fsneakers.features.product.services;

import java.util.List;
import java.util.UUID;

import com.sd51.fsneakers.features.product.dto.request.DeGiayRequest;
import com.sd51.fsneakers.features.product.dto.response.DeGiayResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sd51.fsneakers.features.product.entity.DeGiay;

public interface DeGiayService {

    List<DeGiayResponse> getAllDeGiay();

    Page<DeGiayResponse> getAllDeGiayPage(Pageable pageable);

    Page<DeGiayResponse> searchDeGiay(String keyword, Integer trangThai, Pageable pageable);

    DeGiay findByMa(String maDeGiay);

    DeGiay findById(UUID id);

    DeGiayResponse createDeGiay(DeGiayRequest request);

    DeGiayResponse updateDeGiay(UUID id, DeGiayRequest request);

    DeGiayResponse deleteDeGiay(UUID id);


}
