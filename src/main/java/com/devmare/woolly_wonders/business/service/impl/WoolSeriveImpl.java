package com.devmare.woolly_wonders.business.service.impl;

import com.devmare.woolly_wonders.business.dto.WoolDto;
import com.devmare.woolly_wonders.business.service.WoolService;
import com.devmare.woolly_wonders.data.entity.Farmer;
import com.devmare.woolly_wonders.data.entity.Wool;
import com.devmare.woolly_wonders.data.enums.WoolType;
import com.devmare.woolly_wonders.data.exception.UserInfoException;
import com.devmare.woolly_wonders.data.repository.FarmerRepository;
import com.devmare.woolly_wonders.data.repository.WoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.devmare.woolly_wonders.data.enums.WoolVerificationStatus.NOT_APPLIED;

@Service
@RequiredArgsConstructor
public class WoolSeriveImpl implements WoolService {

    private final WoolRepository woolRepository;
    private final FarmerRepository farmerRepository;

    @Override
    public Wool createWool(WoolDto woolDto, Long farmerId) {
        Optional<Farmer> optionalFarmer = farmerRepository.findById(farmerId);
        if (optionalFarmer.isEmpty()) {
            throw new UserInfoException("Farmer not found");
        }
        Farmer farmer = optionalFarmer.get();

        boolean isValidWoolType = false;
        for (WoolType woolType : WoolType.values()) {
            if (woolType.equals(woolDto.getWoolType())) {
                isValidWoolType = true;
                break;
            }
        }
        if (!isValidWoolType) {
            throw new UserInfoException("Invalid wool type");
        }

        Wool wool = Wool.builder()
                .weight(woolDto.getWeight())
                .woolType(woolDto.getWoolType())
                .farmer(farmer)
                .woolVerificationStatus(NOT_APPLIED)
                .build();

        Wool newWool = woolRepository.save(wool);
        farmer.getWools().add(newWool);
        farmerRepository.save(farmer);
        return newWool;
    }

    @Override
    public List<Wool> getWoolsByFarmerId(Long farmerId) {
        Optional<Farmer> optionalFarmer = farmerRepository.findById(farmerId);
        if (optionalFarmer.isEmpty()) {
            throw new UserInfoException("Farmer not found");
        }
        Farmer farmer = optionalFarmer.get();
        return farmer.getWools();
    }

    @Override
    public List<Wool> getAllWools() {
        return woolRepository.findAll();
    }
}
