package com.dent.dentclinicapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class AppointmentDto
{
    private Long id;
    private String name;
    private String surname;
    private String pesel;
    private String email;
    private LocalDate date;
    private Dentist dentist;
    private Services service;
}
