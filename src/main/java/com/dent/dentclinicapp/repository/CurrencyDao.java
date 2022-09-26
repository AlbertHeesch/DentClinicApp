package com.dent.dentclinicapp.repository;

import com.dent.dentclinicapp.domain.Currency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CurrencyDao extends CrudRepository<Currency, Long>
{
    List<Currency> findAll();
}
