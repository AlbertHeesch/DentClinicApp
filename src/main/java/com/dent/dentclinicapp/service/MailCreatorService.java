package com.dent.dentclinicapp.service;

import com.dent.dentclinicapp.config.CompanyConfig;
import com.dent.dentclinicapp.domain.AppointmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService
{
    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    private CompanyConfig config;

    public String buildAppointmentEmail(AppointmentDto appointmentDto) {
        List<String> appointmentDetails = new ArrayList<>();
        appointmentDetails.add(appointmentDto.getDate().toString());
        appointmentDetails.add(appointmentDto.getService().getDescription());
        appointmentDetails.add("Your specialist: " + appointmentDto.getDentist().getName());

        Context context = new Context();
        context.setVariable("patient_name", appointmentDto.getName());
        context.setVariable("company_name", config.getCompanyName());
        context.setVariable("company_address", config.getCompanyAddress());
        context.setVariable("company_email", config.getCompanyEmail());
        context.setVariable("company_phone", config.getCompanyPhone());
        context.setVariable("details", appointmentDetails);
        return templateEngine.process("mail/created-appointment-mail", context);
    }
}
