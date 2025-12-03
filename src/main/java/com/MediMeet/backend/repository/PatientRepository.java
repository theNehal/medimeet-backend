package com.MediMeet.backend.repository;

import com.MediMeet.backend.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Optional<Patient> findByEmail(String email);
}
