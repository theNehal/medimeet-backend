package com.MediMeet.backend.controller;

import com.MediMeet.backend.model.Doctor;
import com.MediMeet.backend.service.DoctorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/{id}")
    public Doctor getDoctor(@PathVariable int id) {
        return doctorService.getDoctorById(id);
    }

    @PostMapping
    public Doctor addDoctor(@RequestBody Doctor doctor) {
        return doctorService.addDoctor(doctor);
    }

    @PutMapping("/{id}")
    public Doctor updateDoctor(@PathVariable int id, @RequestBody Doctor doctor) {
        return doctorService.updateDoctor(id, doctor);
    }

    @DeleteMapping("/{id}")
    public String deleteDoctor(@PathVariable int id) {
        return doctorService.deleteDoctor(id) ? "Doctor deleted" : "Doctor not found";
    }

    @GetMapping("/specialization/{specialization}")
    public List<Doctor> getBySpecialization(@PathVariable String specialization) {
        return doctorService.findBySpecialization(specialization);
    }
}
