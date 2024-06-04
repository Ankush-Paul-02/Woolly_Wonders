package com.devmare.woolly_wonders.business.service;

import com.devmare.woolly_wonders.business.dto.FarmerDto;
import com.devmare.woolly_wonders.business.dto.UpdateFarmerDto;

import java.util.List;

public interface FarmerService {

    FarmerDto updateFarmer(Long id, UpdateFarmerDto updateFarmerDto);

    FarmerDto getFarmerById(Long id);

    List<FarmerDto> getAllFarmers();
}
