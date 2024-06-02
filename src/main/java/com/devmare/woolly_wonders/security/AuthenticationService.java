package com.devmare.woolly_wonders.security;

import com.devmare.woolly_wonders.business.dto.AuthRequestDto;
import com.devmare.woolly_wonders.business.dto.AuthResponseDto;
import com.devmare.woolly_wonders.business.dto.LoginRequestDto;
import com.devmare.woolly_wonders.data.entity.Farmer;
import com.devmare.woolly_wonders.data.entity.RefreshToken;
import com.devmare.woolly_wonders.data.entity.Roles;
import com.devmare.woolly_wonders.data.enums.FarmerStatus;
import com.devmare.woolly_wonders.data.enums.Role;
import com.devmare.woolly_wonders.data.exception.UserInfoException;
import com.devmare.woolly_wonders.data.repository.FarmerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class AuthenticationService {

    private final FarmerRepository farmerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;

    public AuthResponseDto registerFarmer(
            AuthRequestDto authRequestDto
    ) {
        Optional<Farmer> optionalFarmer = farmerRepository.findByEmail(authRequestDto.getEmail());
        if (optionalFarmer.isPresent()) {
            throw new UserInfoException("User with email " + authRequestDto.getEmail() + " already exists");
        }

        Farmer farmer = new Farmer();
        farmer.setEmail(authRequestDto.getEmail());
        farmer.setPassword(passwordEncoder.encode(authRequestDto.getPassword()));
        farmer.setAccountNonLocked(true);
        farmer.setAccountNonExpired(true);
        farmer.setCredentialNonExpired(true);
        farmer.setEnabled(true);
        farmer.setName(authRequestDto.getName());
        farmer.setStatus(FarmerStatus.ACTIVE);

        Set<Roles> defaultRoles = new HashSet<>();
        Roles role = new Roles();
        role.setName(Role.FARMER);
        defaultRoles.add(role);
        farmer.setRoles(defaultRoles);
        Farmer savedFarmer = farmerRepository.save(farmer);

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(savedFarmer.getUsername());
        String jwtToken = jwtService.createToken(new HashMap<>(), savedFarmer);

        return AuthResponseDto
                .builder()
                .jwtToken(jwtToken)
                .refreshToken(refreshToken.getToken())
                .build();
    }

    public AuthResponseDto login(
            LoginRequestDto loginRequestDto
    ) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getEmail(),
                        loginRequestDto.getPassword()
                )
        );

        Optional<Farmer> optionalFarmer = farmerRepository.findByEmail(loginRequestDto.getEmail());
        if (optionalFarmer.isEmpty()) {
            throw new UserInfoException("User with email " + loginRequestDto.getEmail() + " does not exist");
        }

        Farmer farmer = optionalFarmer.get();
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), farmer.getPassword())) {
            throw new UserInfoException("Invalid password for user with email " + loginRequestDto.getEmail());
        }

        String jwtToken = jwtService.createToken(new HashMap<>(), farmer);
        return AuthResponseDto
                .builder()
                .jwtToken(jwtToken)
                .refreshToken(farmer.getRefreshToken().getToken())
                .build();
    }
}
