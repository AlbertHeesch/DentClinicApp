package com.dent.dentclinicapp.repository;

import com.dent.dentclinicapp.domain.Appointment;
import com.dent.dentclinicapp.domain.Dentist;
import com.dent.dentclinicapp.domain.Services;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AppointmentDaoTest
{
    @Autowired
    private AppointmentDao appointmentDao;

    @Test
    void testFindByAll()
    {
        //Given
        Appointment appointment = new Appointment("sadas", "wqeqwe", "dasasd", "2ewqd", LocalDate.now());
        appointmentDao.save(appointment);

        //When
        List<Appointment> list = appointmentDao.findAll();

        //Then
        assertEquals(1, list.size());

    }

}