package com.dent.dentclinicapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class DentistDto
{
    private Long id;
    private String name;
    private String surname;
    private Integer experience;
    private List<Appointment> appointmentList;
}
