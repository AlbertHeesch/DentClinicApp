package com.dent.dentclinicapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "CLINICS")
public class Clinic {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "CLINIC_ID", unique = true)
    private Long id;

    @NotNull
    @Column(name = "NAME")
    private  String name;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    @OneToMany(
            targetEntity = Dentist.class,
            mappedBy = "clinic",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Dentist> dentists;
}
