package com.medical.medical.model;

import java.time.LocalDate;

public class Visit {
    private Long id;
    private Long patientId;
    private LocalDate date;
    private String reason;
    private String diagnosis;
    private String doctor;

    public Visit() {}

    public Visit(Long id, Long patientId, LocalDate date,
                 String reason, String diagnosis, String doctor) {
        this.id = id;
        this.patientId = patientId;
        this.date = date;
        this.reason = reason;
        this.diagnosis = diagnosis;
        this.doctor = doctor;
    }

    public Long getId() { return id; }
    public Long getPatientId() { return patientId; }
    public LocalDate getDate() { return date; }
    public String getReason() { return reason; }
    public String getDiagnosis() { return diagnosis; }
    public String getDoctor() { return doctor; }

    public void setId(Long id) { this.id = id; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }
    public void setDate(LocalDate date) { this.date = date; }
    public void setReason(String reason) { this.reason = reason; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }
    public void setDoctor(String doctor) { this.doctor = doctor; }
}
