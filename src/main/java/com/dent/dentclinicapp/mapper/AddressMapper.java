package com.dent.dentclinicapp.mapper;

import com.dent.dentclinicapp.domain.Address;
import com.dent.dentclinicapp.domain.AddressDto;
import org.springframework.stereotype.Service;

@Service
public class AddressMapper {
    public Address mapToAddress(final AddressDto addressDto) {
        return new Address(
                addressDto.getId(),
                addressDto.getCity(),
                addressDto.getStreet(),
                addressDto.getBuildingNo(),
                addressDto.getPostalCode(),
                addressDto.getPhoneNo(),
                addressDto.getLatitude(),
                addressDto.getLongitude()
        );
    }

    public AddressDto mapToAddressDto(final Address address) {
        return new AddressDto(
                address.getId(),
                address.getCity(),
                address.getStreet(),
                address.getBuildingNo(),
                address.getPostalCode(),
                address.getPhoneNo(),
                address.getLatitude(),
                address.getLongitude()
                );
    }
}
