package com.sd51.fsneakers.features.product.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sd51.fsneakers.features.product.entity.HangGiay;

public interface HangGiayService {

    List<HangGiay> getAllHangGiay();

    HangGiay findByMa(String ma);

    HangGiay createHangGiay(HangGiay hangGiay);

    HangGiay updateHangGiay(String ma, HangGiay hangGiayUpdate);

    HangGiay deleteHangGiay(String ma);

    Page<HangGiay> getAllHangGiayPage(Pageable pageable);

    Page<HangGiay> searchHangGiay(String keyword, Integer trangThai, Pageable pageable);
}
