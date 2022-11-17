package com.dent.dentclinicapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class DentistDto
{
    private Long id;
    private String name;
    private String surname;
    private LocalDate experience;
}
