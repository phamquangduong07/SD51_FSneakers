package com.sd51.fsneakers.features.order.services.impl;



import com.sd51.fsneakers.features.order.dtos.requests.SanPhamChiTietRequest;
import com.sd51.fsneakers.features.order.dtos.requests.ThanhToanRequest;
import com.sd51.fsneakers.features.order.dtos.response.HoaDonChiTietResponse;
import com.sd51.fsneakers.features.order.dtos.response.HoaDonResponse;
import com.sd51.fsneakers.features.order.entity.HoaDon;
import com.sd51.fsneakers.features.order.entity.HoaDonChiTiet;
import com.sd51.fsneakers.features.order.entity.ThanhToan;
import com.sd51.fsneakers.features.order.enums.OrderStatus;
import com.sd51.fsneakers.features.order.enums.ThanhToanMethod;
import com.sd51.fsneakers.features.order.mapper.HoaDonChiTietMapper;
import com.sd51.fsneakers.features.order.mapper.HoaDonMapper;
import com.sd51.fsneakers.features.order.repositories.HoaDonChiTietRepository;
import com.sd51.fsneakers.features.order.repositories.HoaDonRepository;
import com.sd51.fsneakers.features.order.repositories.ThanhToanRepository;
import com.sd51.fsneakers.features.order.services.HoaDonService;
import com.sd51.fsneakers.features.product.entity.SanPhamChiTiet;
import com.sd51.fsneakers.features.product.repositories.SanPhamChiTietRepository;

import com.sd51.fsneakers.features.user.entity.KhachHang;
import com.sd51.fsneakers.features.user.entity.NhanVien;
import com.sd51.fsneakers.features.user.repository.KhachHangRepository;
import com.sd51.fsneakers.features.user.repository.NhanVienRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HoaDonServiceImpl implements HoaDonService {
    private final HoaDonRepository hoaDonRepository;
    private final HoaDonChiTietRepository hoaDonChiTietRepository;

    private final NhanVienRepository nhanVienRepository;
    private final SanPhamChiTietRepository sanPhamChiTietRepository;
    private final KhachHangRepository khachHangRepository;
    private final ThanhToanRepository thanhToanRepository;

    @Override
    public HoaDonResponse createHoaDon(String maNhanVien) {

        NhanVien nv = nhanVienRepository.findNhanVienByNguoiDung_Ma(maNhanVien)
                .orElseThrow(() -> new RuntimeException("Nhân viên không tồn tại"));

        HoaDon hd = HoaDon.builder()
                .ma(generateHoaDonCode(maNhanVien))
                .loaiHoaDon("TAI_QUAY")
                .status(OrderStatus.MOI_TAO)
                .nhanVien(nv)
                .ghiChu("Mua hàng tại quầy")
                .build();

        hoaDonRepository.save(hd);
        return HoaDonMapper.toHoaDonResponse(hd);
    }
    private String generateHoaDonCode(String maNhanVien) {
        String prefix = "HD";
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss"));

        return prefix + dateTime + maNhanVien;
    }

    @Transactional
    public HoaDonChiTietResponse addSanPham(String maHoaDon, SanPhamChiTietRequest req) {
        // 1️⃣ Tìm hóa đơn
        HoaDon hoaDon = hoaDonRepository.findByMa(maHoaDon)
                .orElseThrow(() -> new RuntimeException("Hóa đơn không tồn tại"));

        // 2️⃣ Tìm chi tiết sản phẩm
        SanPhamChiTiet ctsp = sanPhamChiTietRepository.findByMa(req.getMaCTSP());
        if (ctsp == null) throw new RuntimeException("Chi tiết sản phẩm không tồn tại!");
        if (ctsp.getSoLuong() <= 0) throw new RuntimeException("Sản phẩm đã hết hàng!");

        // 3️⃣ Xác định số lượng cần thêm
        int soLuongThem = (req.getSoLuong() == null || req.getSoLuong() <= 0) ? 1 : req.getSoLuong();
        if (ctsp.getSoLuong() < soLuongThem)
            throw new RuntimeException("Không đủ hàng tồn kho!");

        // 4️⃣ Kiểm tra sản phẩm đã có trong hóa đơn chưa
        Optional<HoaDonChiTiet> existingOpt = hoaDonChiTietRepository
                .findHoaDonChiTietByHoaDon_IdAndChiTietSanPham_Id(hoaDon.getId(), ctsp.getId());

        HoaDonChiTiet hdct;
        if (existingOpt.isPresent()) {
            // 👉 Nếu có rồi → cập nhật số lượng
            hdct = existingOpt.get();
            hdct.setSoLuong(hdct.getSoLuong() + soLuongThem);
        } else {
            // 👉 Nếu chưa có → thêm mới
            hdct = new HoaDonChiTiet();
            hdct.setHoaDon(hoaDon);
            hdct.setChiTietSanPham(ctsp);
            hdct.setSoLuong(soLuongThem);
            hdct.setDonGia(ctsp.getGiaBan());
            hdct.setTrangThai(1);
        }

        // 5️⃣ Lưu lại chi tiết hóa đơn
        hoaDonChiTietRepository.save(hdct);

        // 6️⃣ Cập nhật tổng tiền hóa đơn
        BigDecimal tongTien = hoaDonChiTietRepository.tinhTongTien(hoaDon.getId());
        hoaDon.setThanhTien(tongTien);
        hoaDonRepository.save(hoaDon);

        // 7️⃣ Trả danh sách chi tiết mới nhất
        List<HoaDonChiTiet> list = hoaDonChiTietRepository.findHoaDonChiTietByHoaDon(hoaDon);

        return HoaDonChiTietMapper.toHoaDonChiTietResponse(hdct);
    }


    @Override
    @Transactional
    public HoaDonChiTietResponse deleteSanPham(UUID hoaDonChiTiet) {

        HoaDonChiTiet hdct = hoaDonChiTietRepository.findById(hoaDonChiTiet)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hóa đơn chi tiết"));
        hoaDonChiTietRepository.delete(hdct);
        return HoaDonChiTietMapper.toHoaDonChiTietResponse(hdct);
    }

    @Override
    public HoaDonChiTietResponse updateSoLuongSanPham(UUID idHDCT, int soLuongMoi) {
        HoaDonChiTiet hdct = hoaDonChiTietRepository.findById(idHDCT)
                .orElseThrow(() -> new RuntimeException(STR."Không tìm thấy hóa đơn chi tiết với id: \{idHDCT}"));



        SanPhamChiTiet sanPham = hdct.getChiTietSanPham();

        if (soLuongMoi > sanPham.getSoLuong()) {
            throw new RuntimeException("Số lượng vượt quá tồn kho (" + sanPham.getSoLuong() + ")");
        }
        hdct.setSoLuong(soLuongMoi);
        hoaDonChiTietRepository.save(hdct);
        HoaDon hoaDon = hdct.getHoaDon();
        // Cập nhật lại tổng tiền hóa đơn
        BigDecimal tongTien = hoaDonChiTietRepository.tinhTongTien(hoaDon.getId());
        hoaDon.setThanhTien(tongTien);
        hoaDonRepository.save(hoaDon);
        return HoaDonChiTietMapper.toHoaDonChiTietResponse(hdct);
    }





    @Override
    public HoaDonResponse addKhachHang(String maHoaDon, String soDienThoai) {
        HoaDon hd = hoaDonRepository.findByMa(maHoaDon)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hóa đơn"));

        Optional<KhachHang> khachHangOpt = khachHangRepository.findByNguoiDungSoDienThoai(soDienThoai);
        if(khachHangOpt.isPresent()){
            KhachHang khachHang = khachHangOpt.get();
            hd.setKhachHang(khachHang);
        }else {
            hd.setSoDienThoai(soDienThoai);
        }

        hd.setKhachHang(khachHangOpt.orElse(null));
        hoaDonRepository.save(hd);
        return HoaDonMapper.toHoaDonResponse(hd);
    }

    @Override
    public HoaDonResponse tinhTien(String  maHoaDon) {
        HoaDon hd = hoaDonRepository.findByMa(maHoaDon)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hóa đơn"));
        hd.setStatus(OrderStatus.CHO_THANH_TOAN); // 2 = CHỜ THANH TOÁN
        hd.setNgayMua(LocalDateTime.now());
        hd.setThanhTien(hoaDonChiTietRepository.tinhTongTien(hd.getId()));
        hoaDonRepository.save(hd);

        HoaDonResponse hoaDonResponse = HoaDonResponse
                .builder()
                .id(hd.getId())
                .ma(hd.getMa())
                .loaiHoaDon(hd.getLoaiHoaDon())
                .giaGoc(hd.getGiaGoc())
                .giaGiamGia(hd.getGiaGiamGia())
                .thanhTien(hd.getThanhTien())
                .trangThaiHoaDon(hd.getStatus().getDescription())
                .phuongThuc(hd.getPhuongThuc())
                .ngayTao(hd.getNgayTao())
                .ngaySua(hd.getNgaySua())
                .maNhanVien(hd.getNhanVien().getNguoiDung().getMa())
                .maKhachHang(hd.getKhachHang() != null ? hd.getKhachHang().getNguoiDung().getMa() : null)
                .chiTietList(getAllHoaDonChiTiet(hd.getId()))
                .build();
        return hoaDonResponse;
    }

    @Override
    public HoaDonResponse thanhToan(String maHoaDon, ThanhToanRequest thanhToanRequest) {
        HoaDon hd = hoaDonRepository.findByMa(maHoaDon)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hóa đơn: " + maHoaDon));

        // Trừ tồn kho chính thức (kiểm tra trước rồi trừ)
        List<HoaDonChiTiet> items = hoaDonChiTietRepository.findHoaDonChiTietByHoaDon_Id(hd.getId());
        for (HoaDonChiTiet item : items) {
            SanPhamChiTiet sp = sanPhamChiTietRepository.findById(item.getChiTietSanPham().getId())
                    .orElseThrow(() -> new RuntimeException("SP không tồn tại: " + item.getChiTietSanPham().getId()));
            if (sp.getSoLuong() < item.getSoLuong()) {
                throw new RuntimeException("Tồn kho không đủ: " + sp.getMa());
            }
        }
        // nếu OK thì thực hiện trừ (tách vòng lặp để rollback dễ hơn nếu dùng transaction)
        for (HoaDonChiTiet item : items) {
            SanPhamChiTiet sp = sanPhamChiTietRepository.findById(item.getChiTietSanPham().getId()).get();
            sp.setSoLuong(sp.getSoLuong() - item.getSoLuong());
            sanPhamChiTietRepository.save(sp);
        }

        // Tính tổng tiền thực tế thanh toán
        BigDecimal tienMat = thanhToanRequest.getTienMat() == null ? BigDecimal.ZERO : thanhToanRequest.getTienMat();
        BigDecimal chuyenKhoan = thanhToanRequest.getChuyenKhoan() == null ? BigDecimal.ZERO : thanhToanRequest.getChuyenKhoan();
        BigDecimal tongThanhToan = tienMat.add(chuyenKhoan);

        if (tongThanhToan.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Số tiền thanh toán không hợp lệ!");
        }

        ThanhToanMethod phuongThuc;
        if (tienMat.compareTo(BigDecimal.ZERO) > 0 && chuyenKhoan.compareTo(BigDecimal.ZERO) > 0) {
            phuongThuc = ThanhToanMethod.KET_HOP;
        } else if (tienMat.compareTo(BigDecimal.ZERO) > 0) {
            phuongThuc = ThanhToanMethod.TIEN_MAT;
        } else {
            phuongThuc = ThanhToanMethod.CHUYEN_KHOAN;
        }

        ThanhToan thanhToan = ThanhToan.builder()
                .hoaDon(hd)
                .tongTien(tongThanhToan)
                .tienMat(tienMat)
                .chuyenKhoan(chuyenKhoan)
                .phuongThuc(phuongThuc.getValue())
                .phuongThucVnp(thanhToanRequest.getPhuongThucVnp())
                .trangThai(1) // 1 = thành công (theo quy ước của bạn)
                .build();

        thanhToanRepository.save(thanhToan);

        hd.setStatus(OrderStatus.DA_THANH_TOAN);
        hd.setSoTienThanhToan(thanhToan.getTongTien());
        hd.setPhuongThuc(phuongThuc.getDescription());
        hoaDonRepository.save(hd);

        return HoaDonMapper.toHoaDonResponse(hd);
    }

    @Override
    public List<HoaDonChiTietResponse> getAllHoaDonChiTiet(UUID hoaDonId) {
        HoaDon hd = hoaDonRepository.findById(hoaDonId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hóa đơn"));

        // Mặc định giá trị để tránh null
        BigDecimal giaGiamGia = hd.getGiaGiamGia() != null ? hd.getGiaGiamGia() : BigDecimal.ZERO;

        List<HoaDonChiTietResponse> list = new ArrayList<>();
        for (HoaDonChiTiet hoaDonChiTiet : hoaDonChiTietRepository.findHoaDonChiTietByHoaDon_Id(hoaDonId)) {
            BigDecimal donGia = hoaDonChiTiet.getDonGia() != null ? hoaDonChiTiet.getDonGia() : BigDecimal.ZERO;

            BigDecimal donGiaSauGiam = donGia.subtract(giaGiamGia);
            if (donGiaSauGiam.compareTo(BigDecimal.ZERO) < 0) {
                donGiaSauGiam = BigDecimal.ZERO; // tránh giá âm
            }

            BigDecimal thanhTien = donGiaSauGiam.multiply(BigDecimal.valueOf(hoaDonChiTiet.getSoLuong()));

            list.add(HoaDonChiTietResponse.builder()
                    .tenSanPham(STR."\{hoaDonChiTiet.getChiTietSanPham().getSanPham().getTen()} (\{hoaDonChiTiet.getChiTietSanPham().getKichThuoc().getTen()})")
                    .soLuong(hoaDonChiTiet.getSoLuong())
                    .donGia(donGiaSauGiam)
                    .thanhTien(thanhTien)
                    .build());
        }

        return list;
    }


    @Override
    public HoaDonResponse deleteHoaDon(String maHoaDon) {
        HoaDon hd = hoaDonRepository.findByMa(maHoaDon)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hóa đơn"));

        hd.setStatus(OrderStatus.DA_HUY);

        hoaDonRepository.save(hd);
        hoaDonChiTietRepository.deleteHoaDonChiTietByHoaDon_Id(hd.getId());

        return HoaDonMapper.toHoaDonResponse(hd);
    }

    /*
    *  @Transactional
    public HoaDon thanhToanTaiQuay(UUID hoaDonId, ThanhToan thanhToan) {
        HoaDon hoaDon = hoaDonRepo.findById(hoaDonId)
                .orElseThrow(() -> new RuntimeException("Hóa đơn không tồn tại"));

        // Nếu khách hàng được truyền trong request
        if (thanhToan.getHoaDon().getKhachHang() != null) {
            KhachHang kh = khachHangRepo.findById(thanhToan.getHoaDon().getKhachHang().getId())
                    .orElseThrow(() -> new RuntimeException("Khách hàng không tồn tại"));
            hoaDon.setKhachHang(kh);
        }

        BigDecimal tongTien = hdctRepo.tinhTongTien(hoaDonId);
        hoaDon.setThanhTien(tongTien);
        hoaDon.setSoTienThanhToan(
                thanhToan.getTienMat().add(thanhToan.getChuyenKhoan()));
        hoaDon.setPhuongThuc("TAI_QUAY");
        hoaDon.setTrangThaiHoaDon(1);
        hoaDonRepo.save(hoaDon);

        thanhToan.setHoaDon(hoaDon);
        thanhToan.setTongTien(tongTien);
        thanhToan.setTrangThai(1);
        thanhToan.setNgayTao(LocalDateTime.now());
        thanhToanRepo.save(thanhToan);

        return hoaDon;
    }*/
}
