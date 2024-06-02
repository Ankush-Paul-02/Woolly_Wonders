package com.devmare.woolly_wonders.security;

import com.devmare.woolly_wonders.data.entity.Farmer;
import com.devmare.woolly_wonders.data.entity.RefreshToken;
import com.devmare.woolly_wonders.data.exception.UserInfoException;
import com.devmare.woolly_wonders.data.repository.FarmerRepository;
import com.devmare.woolly_wonders.data.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final FarmerRepository farmerRepository;

    public RefreshToken createRefreshToken(
            String username
    ) {
        Optional<Farmer> optionalFarmer = farmerRepository.findByEmail(username);
        if (optionalFarmer.isEmpty()) {
            throw new UserInfoException("User not found");
        }
        Farmer farmer = optionalFarmer.get();
        RefreshToken refreshToken = RefreshToken
                .builder()
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(864000000))
                .farmer(farmer)
                .build();
        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken verifyExpiration(
            RefreshToken refreshToken
    ) {
        if (refreshToken.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(refreshToken);
            throw new UserInfoException("Refresh token expired, please login again");
        }
        return refreshToken;
    }

    public RefreshToken findByToken(String token) {
        Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByToken(token);
        if (optionalRefreshToken.isEmpty()) {
            throw new UserInfoException("Invalid refresh token");
        }
        return optionalRefreshToken.get();
    }
}
