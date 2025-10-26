package com.sd51.fsneakers.User.Controller;

import com.sd51.fsneakers.User.Service.KhachHangService;
import com.sd51.fsneakers.User.Entity.KhachHang;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/khach-hang")
public class KhachHangController {
    private final KhachHangService khService;

    @Autowired
    public KhachHangController(KhachHangService kHService){
        this.khService=kHService;
    }

    @GetMapping
    public ResponseEntity<List<KhachHang>> getAll(){
        return ResponseEntity.ok(khService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        var opt = khService.getById(id);
        if (opt.isPresent()) {
            return ResponseEntity.ok(opt.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("KhachHang not found");
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody KhachHang KhachHang, BindingResult br) {
        if (br.hasErrors()) {
            Map<String, String> errors = new java.util.HashMap<>();
            br.getFieldErrors().forEach(fe -> errors.put(fe.getField(), fe.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }
        KhachHang saved = khService.save(KhachHang);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // Xóa khách hàng
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        khService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
