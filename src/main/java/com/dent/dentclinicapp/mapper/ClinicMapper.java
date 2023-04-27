package com.dent.dentclinicapp.mapper;

import com.dent.dentclinicapp.domain.Clinic;
import com.dent.dentclinicapp.domain.ClinicDto;
import org.springframework.stereotype.Service;

@Service
public class ClinicMapper {
    private Clinic mapToClinic(final ClinicDto clinicDto) {
        return new Clinic(
                clinicDto.getId(),
                clinicDto.getName(),
                clinicDto.getAddress(),
                clinicDto.getDentists()
        );
    }

    private ClinicDto mapToClinicDto(final Clinic clinic) {
        return new ClinicDto(
                clinic.getId(),
                clinic.getName(),
                clinic.getAddress(),
                clinic.getDentists()
        );
    }
}
