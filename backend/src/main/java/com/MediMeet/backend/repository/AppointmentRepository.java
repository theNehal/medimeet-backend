package com.MediMeet.backend.repository;

import com.MediMeet.backend.model.Appointment;
import com.MediMeet.backend.model.Patient;
import com.MediMeet.backend.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    List<Appointment> findByPatient(Patient patient);

    List<Appointment> findByDoctor(Doctor doctor);

    List<Appointment> findByDoctorAndAppointmentDateBetween(
            Doctor doctor, LocalDateTime start, LocalDateTime end
    );
}

