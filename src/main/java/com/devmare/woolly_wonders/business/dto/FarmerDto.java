package com.devmare.woolly_wonders.business.dto;

import com.devmare.woolly_wonders.data.entity.Address;
import com.devmare.woolly_wonders.data.entity.Wool;
import com.devmare.woolly_wonders.data.enums.FarmerStatus;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FarmerDto {

    private boolean isGovtCertified;
    private FarmerStatus status;
    private List<Wool> wools;
    private String name;
    private String phoneNumber;
    private String email;
    private boolean isEmailVerified;
    private Address address;
}
