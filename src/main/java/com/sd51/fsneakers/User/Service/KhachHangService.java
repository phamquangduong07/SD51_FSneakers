package com.sd51.fsneakers.User.Service;

import com.sd51.fsneakers.User.Repository.KhachHangRepo;
import com.sd51.fsneakers.User.Entity.KhachHang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class KhachHangService {

    private final KhachHangRepo repo;

    @Autowired
    public KhachHangService(KhachHangRepo repo) {
        this.repo = repo;
    }



    public List<KhachHang> getAll() {
        return repo.findAll();
    }

    public Optional<KhachHang> getById(UUID id) {
        return repo.findById(id);
    }

    public KhachHang save(KhachHang kh) {
        return repo.save(kh);
    }

    public void deleteById(UUID id) {
        repo.deleteById(id);
    }

}
