package com.dent.dentclinicapp.domain;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Dentist
{
    private final String name;
    private final String surname;
    private final LocalDate experience;
}
