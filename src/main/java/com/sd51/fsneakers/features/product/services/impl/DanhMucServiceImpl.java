package com.sd51.fsneakers.features.product.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sd51.fsneakers.features.product.entity.DanhMuc;
import com.sd51.fsneakers.features.product.repositories.DanhMucRepository;
import com.sd51.fsneakers.features.product.services.DanhMucService;

@Service
public class DanhMucServiceImpl implements DanhMucService {

    @Autowired
    private DanhMucRepository danhMucRepository;

    @Override
    public DanhMuc createDanhMuc(DanhMuc danhMuc) {
        if (findByMa(danhMuc.getMa()) != null) {
            throw new RuntimeException("Mã danh mục '" + danhMuc.getMa() + "' đã tồn tại!");
        }

        return danhMucRepository.save(danhMuc);
    }

    @Override
    public DanhMuc findByMa(String ma) {
        return danhMucRepository.findByMa(ma);
    }

    @Override
    public DanhMuc updateDanhMucByMa(String ma, DanhMuc danhMucNew) {
        DanhMuc existing = findByMa(ma);
        if(existing== null){
            throw new RuntimeException("Mã danh mục '" + ma + "' không tồn tại!");
        }
        if(!danhMucNew.getMa().equals(ma)){
            if(findByMa(danhMucNew.getMa())!=null){
                throw new RuntimeException("Mã danh mục '" + danhMucNew.getMa() + "' đã tồn tại khác !");
            }else {

            }
        }
        existing.setMa(danhMucNew.getMa());
        existing.setTen(danhMucNew.getTen());
        existing.setTrangThai(danhMucNew.getTrangThai());
        return danhMucRepository.save(existing);
    }

    @Override
    public List<DanhMuc> fillAll() {
        return danhMucRepository.findAll();
    }

    @Override
    public DanhMuc deleteDanhMuc(String ma) {
        DanhMuc existing = findByMa(ma);
        if(existing== null){
            throw new RuntimeException("Mã danh mục '" + ma + "' không tồn tại!");
        }
        danhMucRepository.delete(existing);
        return existing;
    }


}
