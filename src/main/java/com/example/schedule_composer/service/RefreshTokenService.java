package com.example.schedule_composer.service;

import com.example.schedule_composer.entity.RefreshToken;
public interface RefreshTokenService {

    RefreshToken createRefreshToken(String email);

    RefreshToken verifyRefreshToken(String refreshToken);
}
