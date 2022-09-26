package com.dent.dentclinicapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DENTISTS")
public class Dentist
{
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "DENTIST_ID", unique = true)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "EXPERIENCE")
    private Integer experience;

    @OneToMany(
            targetEntity = Appointment.class,
            mappedBy = "dentist",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @NotNull
    @Column(name = "APPOINTMENTS")
    private List<Appointment> appointmentList = new ArrayList<>();
}
