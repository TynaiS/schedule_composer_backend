package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.get.AuthenticationDTOGet;
import com.example.schedule_composer.dto.post.*;
import com.example.schedule_composer.entity.RefreshToken;
import com.example.schedule_composer.entity.User;
import com.example.schedule_composer.security.JwtService;
import com.example.schedule_composer.service.AuthenticationService;
import com.example.schedule_composer.service.RefreshTokenService;
import com.example.schedule_composer.utils.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Jwt Authentication API", description = "Endpoints for authentication based on jwt")
public class AuthenticationController {

    private final AuthenticationService service;
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;


    @PostMapping("/register")
    @Operation(summary = "Register new user", description = "Register new user and send verification code to email, to verify new account")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterDTOPost request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/login")
    @Operation(summary = "Log in to registered and verified account")
    public ResponseEntity<AuthenticationDTOGet> login (@Valid @RequestBody LoginDTOPost request){
        return ResponseEntity.ok(service.login(request));
    }

    @PostMapping("/verify")
    @Operation(summary = "Verify registered account", description = "Verify registered account using verification code sent on email")
    public ResponseEntity<String> verifyUser(@Valid @RequestBody VerifyUserDTOPost verifyUserDto) {
        return ResponseEntity.ok(service.verifyUser(verifyUserDto));
    }

    @PostMapping("/resend-verification")
    @Operation(summary = "Resend verification code", description = "Resend verification code for verifying new registered account, in case has expired after sending it inside /register endpoint")
    public ResponseEntity<?> resendVerificationCode(@Valid @RequestBody EmailDTOPost email) {
        return ResponseEntity.ok(service.resendVerificationCode(email));
    }

    @PostMapping("/refresh")
    @Operation(summary = "Refresh access token", description = "Refresh access token using refresh token, in case access token has expired")
    public ResponseEntity<AuthenticationDTOGet> refreshToken(@Valid @RequestBody RefreshTokenDTOPost refreshTokenRequest) {
        return ResponseEntity.ok(service.refreshToken(refreshTokenRequest));
    }
}
