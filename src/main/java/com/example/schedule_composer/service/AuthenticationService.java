package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.AuthenticationDTOGet;
import com.example.schedule_composer.dto.post.*;
import com.example.schedule_composer.entity.User;

public interface AuthenticationService {
    String register(RegisterDTOPost request);

    AuthenticationDTOGet login(LoginDTOPost loginRequest);

    String verifyUser(VerifyUserDTOPost input);

    String resendVerificationCode(EmailDTOPost email);


//    ResetPasswordDTOGet resetPassword(ResetPasswordDTOPost request);

    void sendVerificationEmail(User user, String verificationCode);

    AuthenticationDTOGet refreshToken(RefreshTokenDTOPost refreshTokenRequest);
}
