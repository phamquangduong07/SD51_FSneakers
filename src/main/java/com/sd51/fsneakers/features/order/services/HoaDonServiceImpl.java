package com.sd51.fsneakers.features.order.services;

import com.sd51.fsneakers.features.order.repositories.HoaDonChiTietRepository;
import com.sd51.fsneakers.features.order.repositories.HoaDonRepository;
import com.sd51.fsneakers.features.product.repositories.SanPhamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HoaDonServiceImpl implements HoaDonService{
    private final HoaDonRepository hoaDonRepository;
    private final HoaDonChiTietRepository hoaDonChiTietRepository;
    private final SanPhamRepository sanPhamChiTietRepository;
}
