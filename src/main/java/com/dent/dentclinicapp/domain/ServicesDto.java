package com.dent.dentclinicapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ServicesDto
{
    private Long id;
    private String description;
    private Double cost;
}
