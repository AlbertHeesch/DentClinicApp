package com.dent.dentclinicapp.repository;

import com.dent.dentclinicapp.domain.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface AddressDao extends CrudRepository<Address, Long> {
}
