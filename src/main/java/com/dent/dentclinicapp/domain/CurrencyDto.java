package com.dent.dentclinicapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CurrencyDto
{
    private Long id;
    private String name;
    private Double value;
}
