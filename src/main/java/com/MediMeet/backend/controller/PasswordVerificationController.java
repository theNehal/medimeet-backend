package com.MediMeet.backend.controller;

import com.MediMeet.backend.model.Admin;
import com.MediMeet.backend.model.Doctor;
import com.MediMeet.backend.service.AdminService;
import com.MediMeet.backend.service.DoctorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/verify")
public class PasswordVerificationController {

    private final DoctorService doctorService;
    private final AdminService adminService;

    public PasswordVerificationController(DoctorService doctorService, AdminService adminService) {
        this.doctorService = doctorService;
        this.adminService = adminService;
    }

    @PostMapping("/doctor")
    public String createDoctor(@RequestBody Doctor doctor) {
        Doctor saved = doctorService.addDoctor(doctor);
        return "Doctor created with hash: " + saved.getPassword_hash();
    }

    @PostMapping("/admin")
    public String createAdmin(@RequestBody Admin admin) {
        Admin saved = adminService.addAdmin(admin);
        return "Admin created with hash: " + saved.getPassword_hash();
    }
}
