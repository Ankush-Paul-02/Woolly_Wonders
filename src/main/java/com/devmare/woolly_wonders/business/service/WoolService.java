package com.devmare.woolly_wonders.business.service;

import com.devmare.woolly_wonders.business.dto.WoolDto;
import com.devmare.woolly_wonders.data.entity.Wool;

public interface WoolService {

    Wool createWool(WoolDto woolDto, Long farmerId);
}
