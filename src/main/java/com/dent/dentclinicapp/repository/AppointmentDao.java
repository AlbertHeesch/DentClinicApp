package com.dent.dentclinicapp.repository;

import com.dent.dentclinicapp.domain.Appointment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface AppointmentDao extends CrudRepository<Appointment, Long>
{
    @Override
    List<Appointment> findAll();

    @Override
    Optional<Appointment> findById(Long id);

    @Override
    Appointment save(Appointment appointment);
}
