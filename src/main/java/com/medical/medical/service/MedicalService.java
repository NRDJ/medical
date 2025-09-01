package com.medical.medical.service;

import com.medical.medical.model.Gender;
import com.medical.medical.model.Patient;
import com.medical.medical.model.Visit;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MedicalService {

    private final List<Patient> patients = new ArrayList<>();
    private final List<Visit> visits = new ArrayList<>();

    @PostConstruct
    public void init() {
        patients.add(new Patient(1L,"12.345.678-5","Ana","Pérez", LocalDate.of(1990,1,5), Gender.FEMALE,"+56 9 1111 1111"));
        patients.add(new Patient(2L,"9.876.543-2","Benito","Rojas", LocalDate.of(1985,4,20), Gender.MALE,"+56 9 2222 2222"));
        patients.add(new Patient(3L,"18.345.678-K","Carla","Gómez", LocalDate.of(1998,12,10), Gender.FEMALE,"+56 9 3333 3333"));
        patients.add(new Patient(4L,"16.222.111-7","Diego","Silva", LocalDate.of(1992,7,1), Gender.MALE,"+56 9 4444 4444"));
        patients.add(new Patient(5L,"21.555.444-3","Elisa","Mella", LocalDate.of(2001,2,14), Gender.FEMALE,"+56 9 5555 5555"));
        patients.add(new Patient(6L,"13.999.888-9","Fabián","Torres", LocalDate.of(1989,9,9), Gender.MALE,"+56 9 6666 6666"));
        patients.add(new Patient(7L,"7.111.222-5","Gabriela","Díaz", LocalDate.of(1975,6,30), Gender.FEMALE,"+56 9 7777 7777"));
        patients.add(new Patient(8L,"10.555.666-1","Héctor","Fuentes", LocalDate.of(1980,11,15), Gender.MALE,"+56 9 8888 8888"));

        visits.add(new Visit(1L,1L, LocalDate.now().minusDays(15), "Control general", "Buen estado", "Dr. Soto"));
        visits.add(new Visit(2L,2L, LocalDate.now().minusDays(10), "Gripe", "Resfrío común", "Dra. Rivas"));
        visits.add(new Visit(3L,3L, LocalDate.now().minusDays(7), "Dolor lumbar", "Lumbago", "Dr. Pérez"));
        visits.add(new Visit(4L,1L, LocalDate.now().minusDays(3), "Chequeo", "Normal", "Dra. Araya"));
        visits.add(new Visit(5L,4L, LocalDate.now().minusDays(20), "Alergia", "Rinitis alérgica", "Dr. Soto"));
        visits.add(new Visit(6L,5L, LocalDate.now().minusDays(1), "Dolor de cabeza", "Cefalea tensional", "Dra. Rivas"));
        visits.add(new Visit(7L,6L, LocalDate.now().minusDays(30), "Lesión deportiva", "Esguince leve", "Dr. Pérez"));
        visits.add(new Visit(8L,7L, LocalDate.now().minusDays(2), "Chequeo", "Normal", "Dra. Araya"));
        visits.add(new Visit(9L,8L, LocalDate.now().minusDays(5), "Gastritis", "Gastritis leve", "Dr. Soto"));
        visits.add(new Visit(10L,3L, LocalDate.now().minusDays(12), "Control", "Mejorando", "Dra. Rivas"));
    }


    public List<Patient> getPatients() {
        return patients;
    }

    public Optional<Patient> getPatient(Long id) {
        return patients.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public boolean isValidRut(String rut) {
        if (rut == null) return false;
        String clean = rut.toUpperCase().replace(".", "").replace("-", "");
        if (clean.length() < 2) return false;

        String number = clean.substring(0, clean.length() - 1);
        char dv = clean.charAt(clean.length() - 1);

        if (!number.chars().allMatch(Character::isDigit)) return false;

        int sum = 0;
        int mul = 2;
        for (int i = number.length() - 1; i >= 0; i--) {
            sum += (number.charAt(i) - '0') * mul;
            mul++;
            if (mul == 8) mul = 2;
        }
        int res = 11 - (sum % 11);
        char expected;
        if (res == 11) expected = '0';
        else if (res == 10) expected = 'K';
        else expected = (char) ('0' + res);

        return expected == dv;
    }

    public Optional<Patient> getPatientByRut(String rut) {
        if (!isValidRut(rut)) return Optional.empty();
        String clean = rut.replace(".", "").toUpperCase();
        return patients.stream()
                .filter(p -> p.getRut().replace(".", "").toUpperCase().equals(clean))
                .findFirst();
    }


    public List<Visit> getVisitsByPatient(Long patientId) {
        return visits.stream().filter(v -> v.getPatientId().equals(patientId)).collect(Collectors.toList());
    }

    public List<Visit> getVisitsInRange(LocalDate from, LocalDate to) {
        return visits.stream()
                .filter(v -> !v.getDate().isBefore(from) && !v.getDate().isAfter(to))
                .collect(Collectors.toList());
    }
}
