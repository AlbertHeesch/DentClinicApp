package com.dent.dentclinicapp.domain;

import com.dent.dentclinicapp.domain.services.delegate.DentistService;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class Appointment
{
    private final Long id;
    private final Dentist dentist;
    private final Patient patient;
    private final LocalDate localDate;
    private final int aproxCost;
    private final DentistService dentistService;
}
