package com.devmare.woolly_wonders.business.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class FarmerDto {

    private String name;
    private String phoneNumber;
    private String email;
}
