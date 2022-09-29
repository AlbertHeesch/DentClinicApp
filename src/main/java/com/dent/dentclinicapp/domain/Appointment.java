package com.dent.dentclinicapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "APPOINTMENTS")
public class Appointment
{
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "APPOINTMENT_ID", unique = true)
    private Long id;

    @NotNull
    @Column(name = "PATIENT_NAME")
    private String name;

    @NotNull
    @Column(name = "PATIENT_SURNAME")
    private String surname;

    @Column(name = "PESEL")
    private String pesel;

    @Column(name = "EMAIL_ADRESS")
    private String email;

    @Column(name = "DATE")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "DENTIST_ID")
    private Dentist dentist;

    @ManyToOne
    @JoinColumn(name = "SERVICE_ID")
    private Services service;
}
