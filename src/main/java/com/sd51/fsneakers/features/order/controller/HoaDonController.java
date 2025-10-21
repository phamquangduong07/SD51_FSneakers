package com.sd51.fsneakers.features.order.controller;


import com.sd51.fsneakers.features.order.dtos.requests.SanPhamChiTietRequest;
import com.sd51.fsneakers.features.order.dtos.requests.ThanhToanRequest;
import com.sd51.fsneakers.features.order.dtos.response.HoaDonChiTietResponse;
import com.sd51.fsneakers.features.order.dtos.response.HoaDonResponse;
import com.sd51.fsneakers.features.order.entity.HoaDon;
import com.sd51.fsneakers.features.order.services.HoaDonService;
import com.sd51.fsneakers.features.product.entity.SanPhamChiTiet;
import com.sd51.fsneakers.features.user.entity.NhanVien;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class HoaDonController {
    private final HoaDonService hoaDonService;


    @PostMapping("/{maNhanVien}")
    public ResponseEntity<?> taoHoaDonTam(@PathVariable String maNhanVien) {

        try{
            HoaDonResponse hoaDon = hoaDonService.createHoaDon(maNhanVien);
            return ResponseEntity.ok(hoaDon);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());


        }

    }

    /** 2️⃣ Thêm sản phẩm vào hóa đơn */
    @PutMapping("/{maHoaDon}")
    public ResponseEntity<?> themSanPhamVaoHoaDon(
            @PathVariable String maHoaDon,
            @RequestBody SanPhamChiTietRequest chiTietSanPham) {

        try{
            HoaDonChiTietResponse hoaDon = hoaDonService.addSanPham(maHoaDon, chiTietSanPham);
            return ResponseEntity.ok(hoaDon);
        } catch (Exception e) {
           return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{hoaDonId}")
    public ResponseEntity<?> getAllHoaDonChiTiet(
            @PathVariable UUID hoaDonId) {

        try{
            return  ResponseEntity.ok(hoaDonService.getAllHoaDonChiTiet(hoaDonId));
        } catch (Exception e) {
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{maHoaDon}/tinh-tien")
    public ResponseEntity<?> tinhTien(
            @PathVariable String maHoaDon) {

        try{
            return  ResponseEntity.ok(hoaDonService.tinhTien(maHoaDon));
        } catch (Exception e) {
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/chi-tiet/{hoaDonChiTietId}")
    public ResponseEntity<?> xoaSanPhamKhoiHoaDon(

            @PathVariable UUID hoaDonChiTietId) {

        try{
            return  ResponseEntity.ok(hoaDonService.deleteSanPham(hoaDonChiTietId));
        } catch (Exception e) {
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{maHoaDon}/{soDienThoai}")
    public ResponseEntity<?> addKhachHang(
            @PathVariable String maHoaDon,
            @PathVariable(value = "soDienThoai") String soDienThoai
            ) {

        try{
            return  ResponseEntity.ok(hoaDonService.addKhachHang(maHoaDon,soDienThoai));
        } catch (Exception e) {
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/thanh-toan/{maHoaDon}")
    public ResponseEntity<?> thanhToanHoaDon(
            @RequestBody ThanhToanRequest thanhToanRequest,
            @PathVariable String maHoaDon
            ) {

        try{
            return  ResponseEntity.ok(hoaDonService.thanhToan(maHoaDon,thanhToanRequest));
        } catch (Exception e) {
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/{maHoaDon}")
    public ResponseEntity<?> deleteHoaDon(@PathVariable String maHoaDon){
        try{
            return  ResponseEntity.ok(hoaDonService.deleteHoaDon(maHoaDon));
        } catch (Exception e) {
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update-so-luong/{id}")
    public ResponseEntity<?> updateSoLuong(
            @PathVariable UUID id,
            @RequestBody Map<String, Integer> body) {



        try {
            int soLuongMoi = body.get("soLuong");
            HoaDonChiTietResponse updated = hoaDonService.updateSoLuongSanPham(id, soLuongMoi);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", e.getMessage());
            error.put("status", 400);
            return ResponseEntity.badRequest().body(error);
        }
    }

}
