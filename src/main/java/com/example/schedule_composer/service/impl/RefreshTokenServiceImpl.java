package com.example.schedule_composer.service.impl;

import com.example.schedule_composer.entity.RefreshToken;
import com.example.schedule_composer.exception.TokenException;
import com.example.schedule_composer.repository.RefreshTokenRepository;
import com.example.schedule_composer.entity.User;
import com.example.schedule_composer.repository.UserRepository;
import com.example.schedule_composer.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;


    private static final long REFRESH_TOKEN_EXPIRATION = 7 * 24 * 60 * 60 * 1000; // 7 days
//    private static final long REFRESH_TOKEN_EXPIRATION = 1 * 60 * 1000; // one minute


    @Transactional
    @Override
    public RefreshToken createRefreshToken(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        refreshTokenRepository.deleteByUserId(user.getId());

        RefreshToken refreshToken = RefreshToken.builder()
                .user(user)
                .refreshToken(UUID.randomUUID().toString() + user.getId())
                .expirationTime(Instant.now().plusMillis(REFRESH_TOKEN_EXPIRATION))
                .build();

        refreshTokenRepository.save(refreshToken);

        return refreshToken;
    }

    @Transactional
    @Override
    public RefreshToken verifyRefreshToken(String refreshToken) {
        RefreshToken refToken = refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new TokenException("Refresh token not found"));

        if(refToken.getExpirationTime().compareTo(Instant.now()) < 0){
            refreshTokenRepository.delete(refToken);
            throw new TokenException("Refresh token expired, please login again");
        }

        return refToken;
    }

}
