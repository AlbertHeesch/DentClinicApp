package com.dent.dentclinicapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ServicesDto
{
    private Long id;
    private String description;
    private Double cost;
    private List<Appointment> appointmentList;
}
