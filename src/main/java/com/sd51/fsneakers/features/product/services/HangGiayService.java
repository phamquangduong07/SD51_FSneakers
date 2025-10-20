package com.sd51.fsneakers.features.product.services;

import java.util.List;

import com.sd51.fsneakers.features.product.entity.HangGiay;

public interface HangGiayService {

    List<HangGiay> getAllHangGiay();

    HangGiay findByMa(String ma);

    HangGiay createHangGiay(HangGiay hangGiay);

    HangGiay updateHangGiay(String ma, HangGiay hangGiayUpdate);

    HangGiay deleteHangGiay(String ma);

}
