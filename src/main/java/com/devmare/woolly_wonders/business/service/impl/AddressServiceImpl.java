package com.devmare.woolly_wonders.business.service.impl;

import com.devmare.woolly_wonders.business.service.AddressService;
import com.devmare.woolly_wonders.data.entity.Address;
import com.devmare.woolly_wonders.data.entity.Farmer;
import com.devmare.woolly_wonders.data.exception.UserInfoException;
import com.devmare.woolly_wonders.data.repository.AddressRepository;
import com.devmare.woolly_wonders.data.repository.FarmerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final FarmerRepository farmerRepository;

    @Override
    public Address createAddress(Long farmerId, Address address) {

        Optional<Farmer> optionalFarmer = farmerRepository.findById(farmerId);
        if (optionalFarmer.isEmpty()) {
            throw new UserInfoException("Farmer not found");
        }
        address.setFarmerId(farmerId);
        return addressRepository.save(address);
    }
}
