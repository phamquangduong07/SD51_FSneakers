package com.sd51.fsneakers.features.product.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sd51.fsneakers.features.product.entity.DanhMuc;
import com.sd51.fsneakers.features.product.services.DanhMucService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/v1/api/danh-muc")
public class DanhMucController {

    @Autowired
    private DanhMucService danhMucService;

    @GetMapping({"", "/"})
    public List<DanhMuc> getAll() {
        return danhMucService.fillAll();
    }

    @PostMapping("/add")
    public DanhMuc createDanhMuc(@RequestBody DanhMuc danhMuc) {
        return ResponseEntity.ok(danhMucService.createDanhMuc(danhMuc)).getBody();
    }

    @PutMapping("/update/{ma}")
    public DanhMuc updateDanhMuc(@PathVariable String ma, @RequestBody DanhMuc danhMuc) {
        return ResponseEntity.ok(danhMucService.updateDanhMucByMa(ma, danhMuc)).getBody();
    }

    @DeleteMapping("/delete/{ma}")
    public DanhMuc deleteDanhMuc(@PathVariable String ma) {
        return ResponseEntity.ok(danhMucService.deleteDanhMuc(ma)).getBody();
    }
    
    
}
