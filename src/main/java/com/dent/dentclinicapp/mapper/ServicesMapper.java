package com.dent.dentclinicapp.mapper;

import com.dent.dentclinicapp.controller.ElementNotFoundException;
import com.dent.dentclinicapp.domain.Services;
import com.dent.dentclinicapp.domain.ServicesDto;
import com.dent.dentclinicapp.service.ServicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServicesMapper {

    private final ServicesService servicesService;

    public Services mapToServices(final ServicesDto servicesDto) throws ElementNotFoundException {
        return new Services(
                servicesDto.getId(),
                servicesDto.getDescription(),
                servicesDto.getCost(),
                servicesService.getService(servicesDto.getId()).getAppointmentList()
        );
    }

    public ServicesDto mapToServicesDto(final Services services)
    {
        return new ServicesDto(
                services.getId(),
                services.getDescription(),
                services.getCost()
        );
    }

    public List<ServicesDto> mapToServicesDtoList(final List<Services> servicesList)
    {
        return servicesList.stream()
                .map(this::mapToServicesDto)
                .collect(Collectors.toList());
    }
}
