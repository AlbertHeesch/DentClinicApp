package com.dent.dentclinicapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;

@AllArgsConstructor
@Getter
@Setter
public class AddressDto {
    private Long id;
    private String city;
    private String street;
    private String buildingNo;
    private Integer postalCode;
    private BigInteger phoneNo;
    private BigDecimal latitude;
    private BigDecimal longitude;
}
