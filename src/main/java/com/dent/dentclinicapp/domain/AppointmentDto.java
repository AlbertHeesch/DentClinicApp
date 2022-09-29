package com.dent.dentclinicapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
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
