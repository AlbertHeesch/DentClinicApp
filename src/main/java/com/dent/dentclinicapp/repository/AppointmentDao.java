package com.dent.dentclinicapp.repository;

import com.dent.dentclinicapp.domain.Appointment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface AppointmentDao extends CrudRepository<Appointment, Long>
{
    List<Appointment> findAll();
}
