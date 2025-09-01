package com.medical.medical.model;

import java.time.LocalDate;

public class Patient {
    private Long id;
    private String rut;          
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Gender gender;
    private String phone;

    public Patient() {}

    public Patient(Long id, String rut, String firstName, String lastName,
                   LocalDate birthDate, Gender gender, String phone) {
        this.id = id;
        this.rut = rut;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phone = phone;
    }

    public Long getId() { return id; }
    public String getRut() { return rut; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public LocalDate getBirthDate() { return birthDate; }
    public Gender getGender() { return gender; }
    public String getPhone() { return phone; }

    public void setId(Long id) { this.id = id; }
    public void setRut(String rut) { this.rut = rut; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
    public void setGender(Gender gender) { this.gender = gender; }
    public void setPhone(String phone) { this.phone = phone; }
}
