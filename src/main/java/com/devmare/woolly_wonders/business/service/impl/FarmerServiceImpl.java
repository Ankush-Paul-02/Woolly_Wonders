package com.devmare.woolly_wonders.business.service.impl;

import com.devmare.woolly_wonders.business.dto.FarmerDto;
import com.devmare.woolly_wonders.business.dto.UpdateFarmerDto;
import com.devmare.woolly_wonders.business.service.FarmerService;
import com.devmare.woolly_wonders.data.entity.Address;
import com.devmare.woolly_wonders.data.entity.Farmer;
import com.devmare.woolly_wonders.data.exception.UserInfoException;
import com.devmare.woolly_wonders.data.repository.AddressRepository;
import com.devmare.woolly_wonders.data.repository.FarmerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FarmerServiceImpl implements FarmerService {

    private final FarmerRepository farmerRepository;
    private final AddressRepository addressRepository;

    @Override
    public FarmerDto updateFarmer(
            Long id,
            UpdateFarmerDto updateFarmerDto
    ) {
        if (updateFarmerDto.getPhoneNumber().isEmpty()) {
            throw new UserInfoException("Phone number is required");
        }

        Optional<Farmer> optionalFarmer = farmerRepository.findById(id);
        if (optionalFarmer.isEmpty()) {
            throw new UserInfoException("Farmer not found");
        }

        Farmer farmer = optionalFarmer.get();

        Optional<Address> optionalAddress = addressRepository.findById(farmer.getAddress().getId());
        if (optionalAddress.isEmpty()) {
            throw new UserInfoException("Address not found");
        }
        Address address = optionalAddress.get();

        farmer.setPhoneNumber(updateFarmerDto.getPhoneNumber());
        farmer.setAddress(address);
        Farmer savedFarmer = farmerRepository.save(farmer);

        return FarmerDto
                .builder()
                .isGovtCertified(savedFarmer.isGovtCertified())
                .status(savedFarmer.getStatus() == null ? null : savedFarmer.getStatus())
                .wools(savedFarmer.getWools() == null ? null : savedFarmer.getWools())
                .name(savedFarmer.getName())
                .phoneNumber(savedFarmer.getPhoneNumber())
                .email(savedFarmer.getEmail())
                .isEmailVerified(savedFarmer.isEmailVerified())
                .address(savedFarmer.getAddress() == null ? null : savedFarmer.getAddress())
                .build();
    }

    @Override
    public FarmerDto getFarmerById(Long id) {
        return null;
    }

    @Override
    public List<FarmerDto> getAllFarmers() {
        return List.of();
    }
}
