package com.medical.medical.controller;

import com.medical.medical.model.Patient;
import com.medical.medical.service.MedicalService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
@Validated
public class PatientController {

    private final MedicalService service;

    public PatientController(MedicalService service) {
        this.service = service;
    }

    // GET /api/patients
    @GetMapping
    public List<Patient> all() {
        return service.getPatients();
    }

    // GET /api/patients/{id}
    @GetMapping("/{id}")
    public Patient byId(@PathVariable @Min(1) Long id) {
        return service.getPatient(id)
                .orElseThrow(() -> new NoSuchElementException("Patient not found"));
    }

    // GET /api/patients/by-rut?rut=12.345.678-5
    @GetMapping("/by-rut")
    public Patient byRut(@RequestParam @NotBlank String rut) {
        // 400 si el RUT es inválido
        if (!service.isValidRut(rut)) {
            throw new IllegalArgumentException("Invalid RUT");
        }
        // 404 si no existe
        return service.getPatientByRut(rut)
                .orElseThrow(() -> new NoSuchElementException("Patient not found"));
    }
}
