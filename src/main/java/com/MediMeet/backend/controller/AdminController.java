package com.MediMeet.backend.controller;

import com.MediMeet.backend.model.Admin;
import com.MediMeet.backend.service.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    public Admin addAdmin(@RequestBody Admin admin) {
        return adminService.addAdmin(admin);
    }

    @GetMapping
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @GetMapping("/{email}")
    public Admin getAdminByEmail(@PathVariable String email) {
        return adminService.getByEmail(email);
    }
}
