package com.medical.medical.api;

import java.time.LocalDate;

public class VisitSummary {
    private Long patientId;
    private int totalVisits;
    private LocalDate lastVisit;

    public VisitSummary(Long patientId, int totalVisits, LocalDate lastVisit) {
        this.patientId = patientId;
        this.totalVisits = totalVisits;
        this.lastVisit = lastVisit;
    }

    public Long getPatientId() { return patientId; }
    public int getTotalVisits() { return totalVisits; }
    public LocalDate getLastVisit() { return lastVisit; }
}
