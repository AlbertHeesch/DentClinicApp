package com.dent.dentclinicapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SERVICES")
public class Services
{
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "SERVICE_ID", unique = true)
    private Long id;

    @NotNull
    @Column(name = "SERVICE_DESCRIPTION")
    private String description;

    @NotNull
    @Column(name = "COST")
    private Double cost;

    @OneToMany(
            targetEntity = Appointment.class,
            mappedBy = "service",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Appointment> appointmentList = new ArrayList<>();
}
