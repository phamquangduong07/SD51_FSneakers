package com.sd51.fsneakers.features.product.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import com.sd51.fsneakers.features.product.entity.DeGiay;
import com.sd51.fsneakers.features.product.services.DeGiayService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("api/v1/de-giay")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DeGiayController {

    DeGiayService deGiayService;

    @GetMapping({ "", "/" })
    public List<DeGiay> getAllDeGiay() {
        return ResponseEntity.ok(deGiayService.getAllDeGiay()).getBody();
    }

    @PostMapping("/add")
    public DeGiay addDeGiay(@RequestBody DeGiay deGiay) {
        return ResponseEntity.ok(deGiayService.createDeGiay(deGiay)).getBody();
    }

    @PutMapping("/update/{ma}")
    public DeGiay updateDeGiay(@PathVariable("ma") String ma, @RequestBody DeGiay deGiay) {
        return ResponseEntity.ok(deGiayService.updateDeGiay(ma, deGiay)).getBody();
    }

    @DeleteMapping("/delete/{ma}")
    public DeGiay deleteDeGiay(@PathVariable("ma") String ma) {
        return ResponseEntity.ok(deGiayService.deleteDeGiay(ma)).getBody();
    }

    @GetMapping("/page")
    public Page<DeGiay> getAllDeGiayPage(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(deGiayService.getAllDeGiayPage(pageable)).getBody();
    }

    @GetMapping("/search")
    public Page<DeGiay> searchDeGiay(@RequestParam (required = false) String keyword,
                                     @RequestParam (required = false) Integer trangThai,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return  deGiayService.searchDeGiay(keyword, trangThai, pageable);
    }


}
