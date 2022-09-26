package com.dent.dentclinicapp.repository;

import com.dent.dentclinicapp.domain.Services;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ServicesDao extends CrudRepository<Services, Long> {
}
