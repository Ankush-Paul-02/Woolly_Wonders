package com.devmare.woolly_wonders.business.service.impl;

import com.devmare.woolly_wonders.business.dto.FarmerDto;
import com.devmare.woolly_wonders.business.dto.UpdateFarmerDto;
import com.devmare.woolly_wonders.business.service.FarmerService;
import com.devmare.woolly_wonders.data.entity.Farmer;
import com.devmare.woolly_wonders.data.exception.UserInfoException;
import com.devmare.woolly_wonders.data.repository.AddressRepository;
import com.devmare.woolly_wonders.data.repository.FarmerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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


        farmer.setPhoneNumber(updateFarmerDto.getPhoneNumber());
        Farmer savedFarmer = farmerRepository.save(farmer);

        return FarmerDto
                .builder()
                .isGovtCertified(savedFarmer.isGovtCertified())
                .status(savedFarmer.getStatus() == null ? null : savedFarmer.getStatus())
                .wools(savedFarmer.getWools() == null ? null : savedFarmer.getWools())
                .name(savedFarmer.getName() == null ? null : savedFarmer.getName())
                .phoneNumber(savedFarmer.getPhoneNumber() == null ? null : savedFarmer.getPhoneNumber())
                .email(savedFarmer.getEmail() == null ? null : savedFarmer.getEmail())
                .isEmailVerified(savedFarmer.isEmailVerified())
                .address(savedFarmer.getAddress() == null ? null : savedFarmer.getAddress())
                .build();
    }

    @Override
    public FarmerDto getFarmerById(Long id) {
        return farmerRepository
                .findById(id)
                .map(farmer -> FarmerDto
                        .builder()
                        .isGovtCertified(farmer.isGovtCertified())
                        .status(farmer.getStatus() == null ? null : farmer.getStatus())
                        .wools(farmer.getWools() == null ? null : farmer.getWools())
                        .name(farmer.getName() == null ? null : farmer.getName())
                        .phoneNumber(farmer.getPhoneNumber() == null ? null : farmer.getPhoneNumber())
                        .email(farmer.getEmail() == null ? null : farmer.getEmail())
                        .isEmailVerified(farmer.isEmailVerified())
                        .address(farmer.getAddress() == null ? null : farmer.getAddress())
                        .build()
                ).orElseThrow(() -> new UserInfoException("Farmer not found"));
    }

    @Override
    public List<FarmerDto> getAllFarmers() {
        return farmerRepository
                .findAll()
                .stream()
                .map(farmer -> FarmerDto
                        .builder()
                        .isGovtCertified(farmer.isGovtCertified())
                        .status(farmer.getStatus() == null ? null : farmer.getStatus())
                        .wools(farmer.getWools() == null ? null : farmer.getWools())
                        .name(farmer.getName() == null ? null : farmer.getName())
                        .phoneNumber(farmer.getPhoneNumber() == null ? null : farmer.getPhoneNumber())
                        .email(farmer.getEmail() == null ? null : farmer.getEmail())
                        .isEmailVerified(farmer.isEmailVerified())
                        .address(farmer.getAddress() == null ? null : farmer.getAddress())
                        .build()
                ).collect(Collectors.toList());
    }
}
