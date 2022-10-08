package com.dent.dentclinicapp.repository;

import com.dent.dentclinicapp.domain.Rate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface RateDao extends CrudRepository<Rate, Long>
{
    @Override
    List<Rate> findAll();

    @Override
    Optional<Rate> findById(Long id);

    @Override
    Rate save(Rate rate);
}
