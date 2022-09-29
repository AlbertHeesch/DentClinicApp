package com.dent.dentclinicapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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
    private LocalDate experience;

    @OneToMany(
            targetEntity = Appointment.class,
            mappedBy = "dentist",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Appointment> appointmentList = new ArrayList<>();
}
