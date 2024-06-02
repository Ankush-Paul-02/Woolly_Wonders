package com.devmare.woolly_wonders.controller;

import com.devmare.woolly_wonders.business.dto.AuthRequestDto;
import com.devmare.woolly_wonders.business.dto.AuthResponseDto;
import com.devmare.woolly_wonders.business.dto.RefreshTokenRequestDto;
import com.devmare.woolly_wonders.data.entity.Farmer;
import com.devmare.woolly_wonders.data.entity.RefreshToken;
import com.devmare.woolly_wonders.security.AuthenticationService;
import com.devmare.woolly_wonders.security.JwtService;
import com.devmare.woolly_wonders.security.RefreshTokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {

    private final AuthenticationService authenticationService;
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/register")
    public ResponseEntity<?> signUp(
            @Valid @RequestBody AuthRequestDto authRequestDto
    ) {
        try {
            AuthResponseDto authResponseDto = authenticationService.registerFarmer(authRequestDto);
            return ResponseEntity.ok(authResponseDto);
        } catch (RuntimeException e) {
            logger.error("Error during registration: ", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @Valid @RequestBody AuthRequestDto authRequestDto
    ) {
        try {
            AuthResponseDto authResponseDto = authenticationService.login(authRequestDto);
            return ResponseEntity.ok(authResponseDto);
        } catch (RuntimeException e) {
            logger.error("Error during login: ", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody RefreshTokenRequestDto refreshTokenRequestDto) {
        try {
            String requestRefreshToken = refreshTokenRequestDto.getRefreshToken();
            RefreshToken refreshToken = refreshTokenService.findByToken(requestRefreshToken);

            if (refreshToken == null) {
                throw new RuntimeException("Refresh token not found");
            }

            RefreshToken verifiedToken = refreshTokenService.verifyExpiration(refreshToken);

            Farmer farmer = refreshToken.getFarmer();
            String newJwtToken = jwtService.createToken(new HashMap<>(), farmer);

            return ResponseEntity.ok(AuthResponseDto.builder()
                    .jwtToken(newJwtToken)
                    .refreshToken(verifiedToken.getToken())
                    .build());
        } catch (RuntimeException e) {
            logger.error("Error during token refresh: ", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
