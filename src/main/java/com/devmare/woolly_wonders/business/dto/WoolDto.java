package com.devmare.woolly_wonders.business.dto;

import com.devmare.woolly_wonders.data.enums.WoolType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WoolDto {

    @NotNull(message = "Wool type is required")
    private WoolType woolType;

    @NotNull(message = "Weight is required")
    @Min(value = 0, message = "Weight must be positive")
    private Double weight;
}