package com.dent.dentclinicapp.controller;

import com.dent.dentclinicapp.domain.Services;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/service")
public class ServicesController
{
    @GetMapping
    public List<Services> getServices()
    {
        return new ArrayList<>();
    }

    @GetMapping(value = "{serviceId}")
    public Services getService(@PathVariable Long serviceId)
    {
        return new Services();
    }

    @PutMapping
    public Services updateService(@RequestBody Services services)
    {
        return new Services();
    }

    @DeleteMapping(value = "{serviceId}")
    public void deleteService(@PathVariable Long serviceId)
    {

    }

    @PostMapping
    public void createService(@RequestBody Services services)
    {

    }
}
