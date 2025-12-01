package com.MediMeet.backend.security;

import com.MediMeet.backend.model.Admin;
import com.MediMeet.backend.model.Doctor;
import com.MediMeet.backend.model.Patient;
import com.MediMeet.backend.repository.AdminRepository;
import com.MediMeet.backend.repository.DoctorRepository;
import com.MediMeet.backend.repository.PatientRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final PatientRepository patientRepo;
    private final DoctorRepository doctorRepo;
    private final AdminRepository adminRepo;

    public CustomUserDetailsService(PatientRepository patientRepo, DoctorRepository doctorRepo, AdminRepository adminRepo) {
        this.patientRepo = patientRepo;
        this.doctorRepo = doctorRepo;
        this.adminRepo = adminRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        // Check Patient
        Patient patient = patientRepo.findByEmail(email).orElse(null);
        if (patient != null) {
            return new CustomUserDetails(patient);
        }

        // Check Doctor
        Doctor doctor = doctorRepo.findByEmail(email).orElse(null);
        if (doctor != null) {
            return new CustomUserDetails(doctor);
        }

        // Check Admin
        Admin admin = adminRepo.findByEmail(email).orElse(null);
        if (admin != null) {
            return new CustomUserDetails(admin);
        }

        // User not found
        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
