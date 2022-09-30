package com.dent.dentclinicapp.repository;

import com.dent.dentclinicapp.domain.Dentist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface DentistDao extends CrudRepository<Dentist, Long>
{
    @Override
    List<Dentist> findAll();

    @Override
    Optional<Dentist> findById(Long id);

    @Override
    Dentist save(Dentist dentist);
}
