package com.dent.dentclinicapp.repository;

import com.dent.dentclinicapp.domain.Clinic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ClinicDao extends CrudRepository<Clinic, Long> {
}
