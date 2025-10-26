package com.sd51.fsneakers.User.Service;

import com.sd51.fsneakers.User.Repository.NguoiDungRepo;
import com.sd51.fsneakers.User.Entity.NguoiDung;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NguoiDungService {
    private final NguoiDungRepo repo;

    @Autowired
    public NguoiDungService(NguoiDungRepo repo) {
        this.repo = repo;
    }

    public List<NguoiDung> getAll() {
        return repo.findAll();
    }

    public Optional<NguoiDung> getById(UUID id) {
        return repo.findById(id);
    }

    public NguoiDung save(NguoiDung nd) {
        return repo.save(nd);
    }

    public void delete(UUID id) {
        repo.deleteById(id);
    }
}
