package com.MediMeet.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthTestController {
    private final com.MediMeet.backend.service.DoctorService doctorService;
    private final com.MediMeet.backend.service.AdminService adminService;

    public AuthTestController(com.MediMeet.backend.service.DoctorService doctorService, com.MediMeet.backend.service.AdminService adminService) {
        this.doctorService = doctorService;
        this.adminService = adminService;
    }

    @GetMapping("/test")
    public String test() {
        return "Auth is working!";
    }

    @org.springframework.web.bind.annotation.PostMapping("/verify/doctor")
    public String createDoctor(@org.springframework.web.bind.annotation.RequestBody com.MediMeet.backend.model.Doctor doctor) {
        com.MediMeet.backend.model.Doctor saved = doctorService.addDoctor(doctor);
        return "Doctor created with hash: " + saved.getPassword_hash();
    }

    @org.springframework.web.bind.annotation.PostMapping("/verify/admin")
    public String createAdmin(@org.springframework.web.bind.annotation.RequestBody com.MediMeet.backend.model.Admin admin) {
        com.MediMeet.backend.model.Admin saved = adminService.addAdmin(admin);
        return "Admin created with hash: " + saved.getPassword_hash();
    }
}

