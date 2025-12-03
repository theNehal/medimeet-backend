package com.MediMeet.backend.service;

import com.MediMeet.backend.model.Patient;
import com.MediMeet.backend.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepo;
    private final org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    public PatientService(PatientRepository patientRepo, org.springframework.security.crypto.password.PasswordEncoder passwordEncoder) {
        this.patientRepo = patientRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Patient> getAllPatients() {
        return patientRepo.findAll();
    }

    public Patient getPatientById(int id) {
        return patientRepo.findById(id).orElse(null);
    }

    public Patient addPatient(Patient patient) {
        // Hash password if provided (assuming DTO maps to password_hash or we handle it here)
        // Since the model has password_hash, we expect the controller/caller to set it or we set it here.
        // If the input JSON has "password", it won't map to "password_hash" automatically unless we have a DTO or setter.
        // But assuming the user sends "password_hash" or we want to support "password" logic:
        
        // Ideally we should use a DTO. But for now, let's assume the input might have raw password in password_hash field 
        // OR we need to handle it. 
        // The user's curl sent "password" AND "password_hash" with the same value.
        // If "password_hash" is set to the raw password, we should hash it.
        
        if (patient.getPassword_hash() != null && !patient.getPassword_hash().startsWith("$2a$")) {
             patient.setPassword_hash(passwordEncoder.encode(patient.getPassword_hash()));
        }
        return patientRepo.save(patient);
    }

    public Patient updatePatient(int id, Patient updated) {
        Patient existing = patientRepo.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        existing.setPhone(updated.getPhone());
        existing.setGender(updated.getGender());
        existing.setDate_of_birth(updated.getDate_of_birth());

        return patientRepo.save(existing);
    }

    public boolean deletePatient(int id) {
        if (!patientRepo.existsById(id)) return false;
        patientRepo.deleteById(id);
        return true;
    }
}
