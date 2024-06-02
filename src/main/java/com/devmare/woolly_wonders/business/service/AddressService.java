package com.devmare.woolly_wonders.business.service;

import com.devmare.woolly_wonders.data.entity.Address;

public interface AddressService {

    Address createAddress(Long farmerId, Address address);
}
