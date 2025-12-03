package com.MediMeet.backend.controller;

import com.MediMeet.backend.model.Patient;
import com.MediMeet.backend.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public Patient getPatient(@PathVariable int id) {
        return patientService.getPatientById(id);
    }

    @PostMapping
    public Patient addPatient(@RequestBody Patient patient) {
        return patientService.addPatient(patient);
    }

    @PutMapping("/{id}")
    public Patient updatePatient(
            @PathVariable int id,
            @RequestBody Patient patient
    ) {
        return patientService.updatePatient(id, patient);
    }

    @DeleteMapping("/{id}")
    public String deletePatient(@PathVariable int id) {
        boolean deleted = patientService.deletePatient(id);
        return deleted ? "Patient deleted" : "Patient not found";
    }
}
