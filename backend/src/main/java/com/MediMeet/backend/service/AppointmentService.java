package com.MediMeet.backend.service;

import com.MediMeet.backend.model.Appointment;
import com.MediMeet.backend.model.Doctor;
import com.MediMeet.backend.model.Patient;
import com.MediMeet.backend.repository.AppointmentRepository;
import com.MediMeet.backend.repository.DoctorRepository;
import com.MediMeet.backend.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepo;
    private final PatientRepository patientRepo;
    private final DoctorRepository doctorRepo;

    public AppointmentService(AppointmentRepository appointmentRepo,
                              PatientRepository patientRepo,
                              DoctorRepository doctorRepo) {
        this.appointmentRepo = appointmentRepo;
        this.patientRepo = patientRepo;
        this.doctorRepo = doctorRepo;
    }

    // ------------------ CREATE APPOINTMENT ----------------------
    public Appointment bookAppointment(Appointment appointment) {

        // Validate patient
        Patient patient = patientRepo.findById(appointment.getPatient().getId()).orElse(null);
        if (patient == null) return null;

        // Validate doctor
        Doctor doctor = doctorRepo.findById(appointment.getDoctor().getId()).orElse(null);
        if (doctor == null) return null;

        // Check appointment conflicts (optional)
        LocalDateTime apptTime = appointment.getAppointmentDate();
        List<Appointment> conflicts = appointmentRepo
                .findByDoctorAndAppointmentDateBetween(
                        doctor,
                        apptTime.minusMinutes(29),
                        apptTime.plusMinutes(29)
                );

        if (!conflicts.isEmpty()) {
            return null; // conflict found
        }

        return appointmentRepo.save(appointment);
    }

    // Get all appointments for a patient
    public List<Appointment> getAppointmentsByPatient(int patientId) {
        Patient patient = patientRepo.findById(patientId).orElse(null);
        if (patient == null) return null;

        return appointmentRepo.findByPatient(patient);
    }

    // Get all appointments for a doctor
    public List<Appointment> getAppointmentsByDoctor(int doctorId) {
        Doctor doctor = doctorRepo.findById(doctorId).orElse(null);
        if (doctor == null) return null;

        return appointmentRepo.findByDoctor(doctor);
    }

    public Appointment updateAppointmentStatus(int apptId, String status) {
        Appointment appt = appointmentRepo.findById(apptId).orElse(null);
        if (appt == null) return null;

        appt.setStatus(status);
        return appointmentRepo.save(appt);
    }

    public boolean deleteAppointment(int id) {
        if (!appointmentRepo.existsById(id)) return false;
        appointmentRepo.deleteById(id);
        return true;
    }
}

