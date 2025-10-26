package com.sd51.fsneakers.User.Controller;

import com.sd51.fsneakers.User.Service.NguoiDungService;
import com.sd51.fsneakers.User.Entity.NguoiDung;
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
@RequestMapping("/api/nguoi-dung")
public class NguoiDungController {
    private final NguoiDungService ndService;

    @Autowired
    public NguoiDungController(NguoiDungService NDService) {
        this.ndService = NDService;
    }

    @GetMapping
    public ResponseEntity<List<NguoiDung>> getAll() {
        return ResponseEntity.ok(ndService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<NguoiDung> opt = ndService.getById(id);
        if (opt.isPresent()) {
            return ResponseEntity.ok(opt.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NguoiDung not found");
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody NguoiDung nd, BindingResult br) {
        if (br.hasErrors()) {
            Map<String, String> errors = new java.util.HashMap<>();
            br.getFieldErrors().forEach(fe -> errors.put(fe.getField(), fe.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }
        NguoiDung saved = ndService.save(nd);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        ndService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
