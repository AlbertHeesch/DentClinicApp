package com.dent.dentclinicapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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
    private LocalDateTime date;
    private Long dentistId;
    private Long serviceId;
}
