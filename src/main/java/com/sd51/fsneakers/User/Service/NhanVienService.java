package com.sd51.fsneakers.User.Service;

import com.sd51.fsneakers.User.Repository.NhanVienRepo;
import com.sd51.fsneakers.User.Entity.NhanVien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NhanVienService {

    private final NhanVienRepo repo;

    @Autowired
    public NhanVienService(NhanVienRepo repo) {
        this.repo = repo;
    }

    public List<NhanVien> getAll() {
        return repo.findAll();
    }

    public Optional<NhanVien> getById(UUID id) {
        return repo.findById(id);
    }

    public NhanVien save(NhanVien nv) {
        return repo.save(nv);
    }

    public void delete(UUID id) {
        repo.deleteById(id);
    }
}
