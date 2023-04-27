package com.dent.dentclinicapp.service;

import com.dent.dentclinicapp.repository.AddressDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {
    @Autowired
    private AddressDao addressDao;
}
