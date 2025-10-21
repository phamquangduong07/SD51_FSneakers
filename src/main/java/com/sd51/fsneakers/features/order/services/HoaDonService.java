package com.sd51.fsneakers.features.order.services;



import com.sd51.fsneakers.features.order.dtos.requests.SanPhamChiTietRequest;
import com.sd51.fsneakers.features.order.dtos.requests.ThanhToanRequest;
import com.sd51.fsneakers.features.order.dtos.response.HoaDonChiTietResponse;
import com.sd51.fsneakers.features.order.dtos.response.HoaDonResponse;
import com.sd51.fsneakers.features.order.entity.HoaDon;
import com.sd51.fsneakers.features.product.entity.SanPhamChiTiet;
import com.sd51.fsneakers.features.user.entity.NhanVien;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface HoaDonService {
    HoaDonResponse createHoaDon(String maNhanVien);
    HoaDonChiTietResponse  addSanPham(String maHoaDon, SanPhamChiTietRequest sanPhamChiTiet);
    HoaDonChiTietResponse deleteSanPham(UUID hoaDonChiTietId);
    HoaDonChiTietResponse updateSoLuongSanPham(UUID idHDCT, int soLuongMoi);
    HoaDonResponse addKhachHang(String maHoaDon, String soDienThoaiKH);
    HoaDonResponse tinhTien(String maHoaDon);
    HoaDonResponse thanhToan(String maHoaDon,ThanhToanRequest thanhToanRequest);
    List<HoaDonChiTietResponse> getAllHoaDonChiTiet(UUID hoaDonId);
    HoaDonResponse deleteHoaDon(String maHoaDon);

    /*
*  // 1️⃣ Tạo hóa đơn tạm cho khách tại quầy
    HoaDonResponse taoHoaDonTam(UUID nhanVienId);

    // 2️⃣ Thêm sản phẩm vào hóa đơn
    HoaDonResponse themSanPhamVaoHoaDon(UUID hoaDonId, UUID chiTietSanPhamId, int soLuong);

    // 3️⃣ Xóa sản phẩm khỏi hóa đơn
    HoaDonResponse xoaSanPhamKhoiHoaDon(UUID hoaDonChiTietId);

    // 4️⃣ Áp dụng voucher (nếu có)
    HoaDonResponse apDungVoucher(UUID hoaDonId, String maVoucher);

    // 5️⃣ Cập nhật thông tin khách hàng (nếu là khách thành viên)
    HoaDonResponse ganKhachHang(UUID hoaDonId, UUID khachHangId);

    // 6️⃣ Tính tổng tiền
    HoaDonResponse tinhTongTien(UUID hoaDonId);

    // 7️⃣ Thanh toán hóa đơn
    HoaDonResponse thanhToanTaiQuay(BanHangTaiQuayRequest request);

    // 8️⃣ Hủy hóa đơn
    void huyHoaDon(UUID hoaDonId);

    // 9️⃣ Lấy danh sách hóa đơn tại quầy đang xử lý
    List<HoaDonResponse> getHoaDonDangXuLy();
* */

}
