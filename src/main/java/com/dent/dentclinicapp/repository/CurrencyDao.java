package com.dent.dentclinicapp.repository;

import com.dent.dentclinicapp.domain.Currency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CurrencyDao extends CrudRepository<Currency, Long>
{
    @Override
    List<Currency> findAll();

    @Override
    Optional<Currency> findById(Long id);

    @Override
    Currency save(Currency currency);
}
