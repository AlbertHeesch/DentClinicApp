package com.dent.dentclinicapp.repository;

import com.dent.dentclinicapp.domain.Services;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ServicesDao extends CrudRepository<Services, Long> {
    @Override
    List<Services> findAll();

    @Override
    Optional<Services> findById(Long id);

    @Override
    Services save(Services services);
}
