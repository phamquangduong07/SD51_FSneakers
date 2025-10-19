package com.sd51.fsneakers.features.product.services;

import java.util.List;

import com.sd51.fsneakers.features.product.entity.DeGiay;

public interface DeGiayService {

    List<DeGiay> getAllDeGiay();

    DeGiay findByMa(String maDeGiay);

    DeGiay createDeGiay(DeGiay deGiay);

    DeGiay updateDeGiay(String ma, DeGiay deGiayUpdate);

    DeGiay deleteDeGiay(String maDeGiay);
}
