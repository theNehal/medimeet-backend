package com.MediMeet.backend.service;

import com.MediMeet.backend.model.Admin;
import com.MediMeet.backend.repository.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final AdminRepository adminRepo;

    public AdminService(AdminRepository adminRepo) {
        this.adminRepo = adminRepo;
    }

    public Admin addAdmin(Admin admin) {
        return adminRepo.save(admin);
    }

    public List<Admin> getAllAdmins() {
        return adminRepo.findAll();
    }

    public Admin getByEmail(String email) {
        return adminRepo.findByEmail(email).orElse(null);
    }
}
