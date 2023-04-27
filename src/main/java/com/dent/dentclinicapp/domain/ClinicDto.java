package com.dent.dentclinicapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ClinicDto {
    private Long id;
    private  String name;
    private AddressDto address;
    private List<DentistDto> dentists;
}
