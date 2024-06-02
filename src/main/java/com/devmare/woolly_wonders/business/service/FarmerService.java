package com.devmare.woolly_wonders.business.service;

import com.devmare.woolly_wonders.business.dto.FarmerDto;
import com.devmare.woolly_wonders.business.dto.UpdateFarmerDto;

public interface FarmerService {

    FarmerDto updateFarmer(Long id, Long addressId, UpdateFarmerDto updateFarmerDto);
}
