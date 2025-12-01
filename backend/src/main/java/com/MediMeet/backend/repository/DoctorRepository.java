package com.MediMeet.backend.repository;

import com.MediMeet.backend.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    // find doctors by exact specialization
    List<Doctor> findBySpecialization(String specialization);

    // optional: search by name containing (case sensitive depends on DB collation)
    // optional: search by name containing (case sensitive depends on DB collation)
    List<Doctor> findByNameContainingIgnoreCase(String namePart);

    java.util.Optional<Doctor> findByEmail(String email);
}
