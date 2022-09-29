package com.dent.dentclinicapp.controller;

import com.dent.dentclinicapp.domain.Services;
import com.dent.dentclinicapp.domain.ServicesDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/service")
public class ServicesController
{
    @GetMapping
    public List<ServicesDto> getServices()
    {
        return new ArrayList<>();
    }

    @GetMapping(value = "{serviceId}")
    public ServicesDto getService(@PathVariable Long serviceId)
    {
        return new ServicesDto();
    }

    @PutMapping
    public ServicesDto updateService(@RequestBody ServicesDto servicesDto)
    {
        return new ServicesDto();
    }

    @DeleteMapping(value = "{serviceId}")
    public void deleteService(@PathVariable Long serviceId)
    {

    }

    @PostMapping
    public void createService(@RequestBody ServicesDto servicesDto)
    {

    }
}
