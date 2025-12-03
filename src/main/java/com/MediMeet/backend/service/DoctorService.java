package com.MediMeet.backend.service;

import com.MediMeet.backend.model.Doctor;
import com.MediMeet.backend.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepo;

    public DoctorService(DoctorRepository doctorRepo) {
        this.doctorRepo = doctorRepo;
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepo.findAll();
    }

    public Doctor getDoctorById(int id) {
        return doctorRepo.findById(id).orElse(null);
    }

    public Doctor addDoctor(Doctor doctor) {
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
