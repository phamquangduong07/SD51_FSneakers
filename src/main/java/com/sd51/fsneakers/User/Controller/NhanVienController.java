package com.sd51.fsneakers.User.Controller;

import com.sd51.fsneakers.User.Service.NhanVienService;
import com.sd51.fsneakers.User.Entity.NhanVien;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/nhan-vien")
public class NhanVienController {

    private final NhanVienService nvService;

    @Autowired
    public NhanVienController(NhanVienService nvService) {
        this.nvService = nvService;
    }

    // Lấy tất cả nhân viên
    @GetMapping
    public ResponseEntity<List<NhanVien>> getAll() {
        return ResponseEntity.ok(nvService.getAll());
    }

    // Lấy nhân viên theo id
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<NhanVien> opt = nvService.getById(id);
        if (opt.isPresent()) {
            return ResponseEntity.ok(opt.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NhanVien not found");
    }

    // Thêm hoặc cập nhật nhân viên
    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody NhanVien nv, BindingResult br) {
        if (br.hasErrors()) {
            Map<String, String> errors = new java.util.HashMap<>();
            br.getFieldErrors().forEach(fe -> errors.put(fe.getField(), fe.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }
        NhanVien saved = nvService.save(nv);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // Xóa nhân viên theo id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        nvService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
