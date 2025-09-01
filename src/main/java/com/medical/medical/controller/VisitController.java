package com.medical.medical.controller;

import com.medical.medical.api.VisitSummary;
import com.medical.medical.model.Visit;
import com.medical.medical.service.MedicalService;
import jakarta.validation.constraints.Min;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/visits")
@Validated
public class VisitController {

    private final MedicalService service;

    public VisitController(MedicalService service) {
        this.service = service;
    }

    // GET /api/visits/by-patient/{patientId}
    @GetMapping("/by-patient/{patientId}")
    public List<Visit> byPatient(@PathVariable @Min(1) Long patientId) {
        return service.getVisitsByPatient(patientId);
    }

    // GET /api/visits?from=YYYY-MM-DD&to=YYYY-MM-DD
    @GetMapping
    public List<Visit> byRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
    ) {
        if (to.isBefore(from)) {
            throw new IllegalArgumentException("`to` must be >= `from`");
        }
        return service.getVisitsInRange(from, to);
    }

    // GET /api/visits/summary?patientId=...
    @GetMapping("/summary")
    public VisitSummary summary(@RequestParam @Min(1) Long patientId) {
        List<Visit> v = service.getVisitsByPatient(patientId);
        LocalDate last = v.stream().map(Visit::getDate).max(LocalDate::compareTo).orElse(null);
        return new VisitSummary(patientId, v.size(), last);
    }
}
