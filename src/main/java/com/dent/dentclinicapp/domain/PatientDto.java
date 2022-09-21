package com.dent.dentclinicapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PatientDto
{
    private final String name;
    private final String surname;
    private final int pesel;
    private final String emailAdress;
    private final int phoneNumber;
}
