package com.MediMeet.backend.config;

import com.MediMeet.backend.model.Admin;
import com.MediMeet.backend.model.Doctor;
import com.MediMeet.backend.model.Patient;
import com.MediMeet.backend.repository.AdminRepository;
import com.MediMeet.backend.repository.DoctorRepository;
import com.MediMeet.backend.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner seedData(PatientRepository patientRepository, 
                                      DoctorRepository doctorRepository, 
                                      AdminRepository adminRepository, 
                                      PasswordEncoder passwordEncoder) {
        return args -> {
            System.out.println("Starting Data Seeding...");
            // Seed Patient
            if (patientRepository.findByEmail("test@example.com").isEmpty()) {
                Patient patient = new Patient();
                patient.setName("Test Patient");
                patient.setEmail("test@example.com");
                patient.setPassword_hash(passwordEncoder.encode("password"));
                patient.setPhone("1234567890");
                patient.setGender("Male");
                patientRepository.save(patient);
                System.out.println("Test patient created: test@example.com / password");
            }

            // Seed Doctor
            if (doctorRepository.findByEmail("doctor@example.com").isEmpty()) {
                Doctor doctor = new Doctor();
                doctor.setName("Dr. Test");
                doctor.setEmail("doctor@example.com");
                doctor.setPassword_hash(passwordEncoder.encode("password"));
                doctor.setSpecialization("General");
                doctor.setAvailable_days("Mon-Fri");
                doctorRepository.save(doctor);
                System.out.println("Test doctor created: doctor@example.com / password");
            }

            // Seed Admin
            Admin admin = adminRepository.findByEmail("admin@example.com").orElse(new Admin());
            admin.setName("Admin User");
            admin.setEmail("admin@example.com");
            admin.setPassword_hash(passwordEncoder.encode("password"));
            admin.setRole("ADMIN");
            adminRepository.save(admin);
            System.out.println("Test admin updated/created: admin@example.com / password");
        };
    }
}
