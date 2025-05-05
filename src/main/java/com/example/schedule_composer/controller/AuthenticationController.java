package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.get.AuthenticationDTOGet;
import com.example.schedule_composer.dto.post.*;
import com.example.schedule_composer.entity.RefreshToken;
import com.example.schedule_composer.entity.User;
import com.example.schedule_composer.security.JwtService;
import com.example.schedule_composer.service.AuthenticationService;
import com.example.schedule_composer.service.RefreshTokenService;
import com.example.schedule_composer.utils.ApiConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstants.AUTHENTICATION)
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterDTOPost request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationDTOGet> login (@Valid @RequestBody LoginDTOPost request){
        return ResponseEntity.ok(service.login(request));
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyUser(@Valid @RequestBody VerifyUserDTOPost verifyUserDto) {
        return ResponseEntity.ok(service.verifyUser(verifyUserDto));
    }

    @PostMapping("/resend-verification")
    public ResponseEntity<?> resendVerificationCode(@Valid @RequestBody EmailDTOPost email) {
        return ResponseEntity.ok(service.resendVerificationCode(email));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationDTOGet> refreshToken(@Valid @RequestBody RefreshTokenDTOPost refreshTokenRequest) {
        return ResponseEntity.ok(service.refreshToken(refreshTokenRequest));
    }
}
