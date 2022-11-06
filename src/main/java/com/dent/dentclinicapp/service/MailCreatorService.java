package com.dent.dentclinicapp.service;

import com.dent.dentclinicapp.config.CompanyConfig;
import com.dent.dentclinicapp.controller.ElementNotFoundException;
import com.dent.dentclinicapp.domain.Appointment;
import com.dent.dentclinicapp.domain.AppointmentDto;
import com.dent.dentclinicapp.mapper.AppointmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MailCreatorService
{
    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    private CompanyConfig config;

    private final AppointmentMapper mapper;

    public String buildAppointmentEmail(Appointment appointment) {
        List<String> appointmentDetails = new ArrayList<>();
        appointmentDetails.add(appointment.getDate().toString().replace("T", " "));
        appointmentDetails.add(appointment.getService().getDescription());
        appointmentDetails.add("Your specialist: " + appointment.getDentist().getName() + " " + appointment.getDentist().getSurname());

        Context context = new Context();
        context.setVariable("patient_name", appointment.getName());
        context.setVariable("company_name", config.getCompanyName());
        context.setVariable("company_address", config.getCompanyAddress());
        context.setVariable("company_email", config.getCompanyEmail());
        context.setVariable("company_phone", config.getCompanyPhone());
        context.setVariable("details", appointmentDetails);
        return templateEngine.process("mail/created-appointment-mail", context);
    }
}
