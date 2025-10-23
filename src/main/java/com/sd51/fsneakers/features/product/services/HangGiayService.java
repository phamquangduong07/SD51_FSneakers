package com.sd51.fsneakers.features.product.services;

import java.util.List;
import java.util.UUID;

import com.sd51.fsneakers.features.product.dto.request.HangGiayRequest;
import com.sd51.fsneakers.features.product.dto.response.HangGiayResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sd51.fsneakers.features.product.entity.HangGiay;

public interface HangGiayService {

    List<HangGiayResponse> getAllHangGiay();

    Page<HangGiayResponse> getAllHangGiayPage(Pageable pageable);

    Page<HangGiayResponse> searchHangGiay(String keyword, Integer trangThai, Pageable pageable);

    HangGiay findByMa(String ma);

    HangGiay findById(UUID id);

    HangGiayResponse createHangGiay(HangGiayRequest request);

    HangGiayResponse updateHangGiay(UUID id, HangGiayRequest request);

    HangGiayResponse deleteHangGiay(UUID id);


}
