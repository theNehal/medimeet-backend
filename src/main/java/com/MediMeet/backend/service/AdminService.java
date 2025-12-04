package com.MediMeet.backend.service;

import com.MediMeet.backend.model.Admin;
import com.MediMeet.backend.repository.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final AdminRepository adminRepo;
    private final org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    public AdminService(AdminRepository adminRepo, org.springframework.security.crypto.password.PasswordEncoder passwordEncoder) {
        this.adminRepo = adminRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public Admin addAdmin(Admin admin) {
        if (admin.getPassword_hash() != null && !admin.getPassword_hash().startsWith("$2a$")) {
            admin.setPassword_hash(passwordEncoder.encode(admin.getPassword_hash()));
        }
        return adminRepo.save(admin);
    }

    public List<Admin> getAllAdmins() {
        return adminRepo.findAll();
    }

    public Admin getByEmail(String email) {
        return adminRepo.findByEmail(email).orElse(null);
    }
}
