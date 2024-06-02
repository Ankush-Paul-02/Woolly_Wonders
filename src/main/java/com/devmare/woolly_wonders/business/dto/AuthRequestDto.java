package com.devmare.woolly_wonders.business.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthRequestDto {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 6, message = "Password should have at least 6 characters")
    private String password;
}
