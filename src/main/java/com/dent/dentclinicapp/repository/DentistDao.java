package com.dent.dentclinicapp.repository;

import com.dent.dentclinicapp.domain.Dentist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface DentistDao extends CrudRepository<Dentist, Long>
{

}
