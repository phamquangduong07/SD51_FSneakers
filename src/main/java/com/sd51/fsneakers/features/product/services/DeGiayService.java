package com.sd51.fsneakers.features.product.services;

import java.util.List;

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

    DeGiayResponse createDeGiay(DeGiayRequest request);

    DeGiayResponse updateDeGiay(String ma, DeGiayRequest request);

    void deleteDeGiay(String maDeGiay);


}
