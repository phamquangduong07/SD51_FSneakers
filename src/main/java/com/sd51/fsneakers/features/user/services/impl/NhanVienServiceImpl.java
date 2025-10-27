package com.sd51.fsneakers.features.user.services.impl;

import com.sd51.fsneakers.features.order.repositories.HoaDonChiTietRepository;
import com.sd51.fsneakers.features.order.repositories.HoaDonRepository;
import com.sd51.fsneakers.features.order.services.HoaDonService;
import com.sd51.fsneakers.features.product.repositories.SanPhamRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NhanVienServiceImpl implements HoaDonService {

    HoaDonRepository hoaDonRepository;

    HoaDonChiTietRepository hoaDonChiTietRepository;

    SanPhamRepository sanPhamChiTietRepository;
}
