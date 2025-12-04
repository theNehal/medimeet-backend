package com.MediMeet.backend.service;

import com.MediMeet.backend.model.Doctor;
import com.MediMeet.backend.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepo;
    private final org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    public DoctorService(DoctorRepository doctorRepo, org.springframework.security.crypto.password.PasswordEncoder passwordEncoder) {
        this.doctorRepo = doctorRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepo.findAll();
    }

    public Doctor getDoctorById(int id) {
        return doctorRepo.findById(id).orElse(null);
    }

    public Doctor addDoctor(Doctor doctor) {
        if (doctor.getPassword_hash() != null && !doctor.getPassword_hash().startsWith("$2a$")) {
            doctor.setPassword_hash(passwordEncoder.encode(doctor.getPassword_hash()));
        }
        return doctorRepo.save(doctor);
    }

    public Doctor updateDoctor(int id, Doctor updated) {
        Doctor existing = doctorRepo.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setName(updated.getName());
        existing.setSpecialization(updated.getSpecialization());
        existing.setAvailable_days(updated.getAvailable_days());
        existing.setEmail(updated.getEmail());

        return doctorRepo.save(existing);
    }

    public boolean deleteDoctor(int id) {
        if (!doctorRepo.existsById(id)) return false;
        doctorRepo.deleteById(id);
        return true;
    }

    public List<Doctor> findBySpecialization(String specialization) {
        return doctorRepo.findBySpecialization(specialization);
    }
}
