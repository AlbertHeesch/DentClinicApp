package com.dent.dentclinicapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ADDRESSES")
public class Address {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ADDRESS_ID", unique = true)
    private Long id;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STREET")
    private String street;

    @Column(name = "BUILDING_NO")
    private String buildingNo;

    @Column(name = "POSTAL_CODE")
    private Integer postalCode;

    @Column(name = "PHONE_NO")
    private BigInteger phoneNo;

    @Column(name = "LATITUDE")
    private BigDecimal latitude;

    @Column(name = "LONGITUDE")
    private BigDecimal longitude;
}
