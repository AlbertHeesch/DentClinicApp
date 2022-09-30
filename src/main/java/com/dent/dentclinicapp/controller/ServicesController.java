package com.dent.dentclinicapp.controller;

import com.dent.dentclinicapp.domain.Services;
import com.dent.dentclinicapp.domain.ServicesDto;
import com.dent.dentclinicapp.mapper.ServicesMapper;
import com.dent.dentclinicapp.service.ServicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/service")
@RequiredArgsConstructor
public class ServicesController
{
    private final ServicesService service;
    private final ServicesMapper mapper;

    @GetMapping
    public ResponseEntity<List<ServicesDto>> getServices()
    {
        List<Services> services = service.getAllServices();
        return ResponseEntity.ok(mapper.mapToServicesDtoList(services));
    }

    @GetMapping(value = "{serviceId}")
    public ResponseEntity<ServicesDto> getService(@PathVariable Long serviceId) throws ElementNotFoundException {
        return ResponseEntity.ok(mapper.mapToServicesDto(service.getService(serviceId)));
    }

    @DeleteMapping(value = "{serviceId}")
    public ResponseEntity<Void> deleteService(@PathVariable Long serviceId) throws ElementNotFoundException
    {
        service.deleteService(service.getService(serviceId));
        return ResponseEntity.ok().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createService(@RequestBody ServicesDto servicesDto)
    {
        Services services = mapper.mapToServices(servicesDto);
        service.saveService(services);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<ServicesDto> updateService(@RequestBody ServicesDto servicesDto)
    {
        Services services = mapper.mapToServices(servicesDto);
        Services savedServices = service.saveService(services);
        return ResponseEntity.ok(mapper.mapToServicesDto(savedServices));
    }
}
