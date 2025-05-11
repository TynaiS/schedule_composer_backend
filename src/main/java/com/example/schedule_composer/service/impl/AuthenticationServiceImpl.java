package com.example.schedule_composer.service.impl;

import com.example.schedule_composer.dto.get.AuthenticationDTOGet;
import com.example.schedule_composer.dto.post.*;
import com.example.schedule_composer.email.MailBody;
import com.example.schedule_composer.entity.RefreshToken;
import com.example.schedule_composer.entity.User;
import com.example.schedule_composer.exception.EmailAlreadyExistsException;
import com.example.schedule_composer.exception.VerificationCodeExpiredException;
import com.example.schedule_composer.repository.UserRepository;
import com.example.schedule_composer.security.JwtService;
import com.example.schedule_composer.service.AuthenticationService;
import com.example.schedule_composer.service.EmailService;
import com.example.schedule_composer.service.RefreshTokenService;
import com.example.schedule_composer.utils.OtpGenerator;
import com.example.schedule_composer.utils.UserRole;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;
    private final EmailService emailService;
    private final UserServiceImpl userService;
    private final UserRepository userRepository;


    @Override
    public String register(RegisterDTOPost request)
        {
        var userAlreadyRegistered = userRepository.existsByEmail(request.getEmail());

        if (!userAlreadyRegistered) {

            String verificationCode = OtpGenerator.otpGenerator();

            var user = User.builder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(UserRole.VIEWER)
                    .isAccountNonLocked(true)
                    .isAccountNonExpired(true)
                    .isCredentialsNonExpired(true)
                    .isEnabled(false)
                    .isEmailVerified(false)
                    .verificationCode(passwordEncoder.encode(verificationCode))
                    .verificationCodeExpiresAt(LocalDateTime.now().plusMinutes(1))
                    .build();
            System.out.println(verificationCode + "  " + user.getVerificationCode());
            sendVerificationEmail(user, verificationCode);
            userRepository.save(user);
            return "Account verification email sent";

        } else {
            throw new EmailAlreadyExistsException("Account with email " + request.getEmail() + " already exists");
        }

    }

    @Override
    public AuthenticationDTOGet login(LoginDTOPost loginRequest) {
        User user = userService.getByEmail(loginRequest.getEmail());

        if (!(user.isEmailVerified())) {
            throw new IllegalStateException("Email is not verified.");
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );



        var accessToken = jwtService.generateToken(user);
        var refreshToken = refreshTokenService.createRefreshToken(loginRequest.getEmail());
        return AuthenticationDTOGet.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getRefreshToken())
                .build();
    }

    @Override
    public String verifyUser(VerifyUserDTOPost input) {
        User user = userService.getByEmail(input.getEmail());
        if(user.isEmailVerified()){
            return "Account is already verified";
        }
        if (user.getVerificationCodeExpiresAt().isBefore(LocalDateTime.now())) {
            throw new VerificationCodeExpiredException("Verification code expired");
        }
        if (passwordEncoder.matches(input.getVerificationCode(), user.getVerificationCode())) {
            user.setEnabled(true);
            user.setEmailVerified(true);
            user.setVerificationCode(null);
            user.setVerificationCodeExpiresAt(null);
            userRepository.save(user);
        } else {
            throw new RuntimeException("Invalid verification code");
        }

        return "Account verified successfully";
    }

    @Override
    public String resendVerificationCode(EmailDTOPost emailDTO) {
        String email = emailDTO.getEmail();
        User user = userService.getByEmail(email);
        if (user.isEmailVerified()) {
            throw new RuntimeException("Account is already verified");
        }
        String verificationCode = OtpGenerator.otpGenerator();
        user.setVerificationCode(passwordEncoder.encode(verificationCode));
        user.setVerificationCodeExpiresAt(LocalDateTime.now().plusMinutes(1));
        sendVerificationEmail(user, verificationCode);
        userRepository.save(user);
        return "Account verification email resent";

    }

    @Override
    public void sendVerificationEmail(User user, String verCode) {
        String subject = "Email verification";
        String verificationCode = "VERIFICATION CODE " + "<div style=\"font-size: 18px; font-weight: bold; color: black;\">" + verCode + "</div>" ;
        String htmlMessage = "<html>"
                + "<body style=\"font-family: Arial, sans-serif;\">"
                + "<div style=\"background-color: #f5f5f5; padding: 20px;\">"
                + "<h2 style=\"color: #333;\">Welcome to Schedule Composer app!</h2>"
                + "<p style=\"font-size: 16px;\">Please enter the verification code below to continue:</p>"
                + "<div style=\"background-color: #fff; padding: 20px; border-radius: 5px; box-shadow: 0 0 10px rgba(0,0,0,0.1);\">"
                + "<h3 style=\"color: #333;\">Verification Code:</h3>"
                + "<p style=\"font-size: 18px; font-weight: bold; color: #007bff;\">" + verificationCode + "</p>"
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>";

        try {
            MailBody mailBody = MailBody.builder()
                    .to(user.getEmail())
                    .subject(subject)
                    .text(htmlMessage)
                    .build();
            emailService.sendVerificationEmail(mailBody);
        } catch (MessagingException e) {
            // Handle email sending exception
            e.printStackTrace();
        }
    }

    @Override
    public AuthenticationDTOGet refreshToken(RefreshTokenDTOPost refreshTokenRequest) {
        RefreshToken refreshToken = refreshTokenService.verifyRefreshToken(refreshTokenRequest.getRefreshToken());
        User user = refreshToken.getUser();

        String accessToken = jwtService.generateToken(user);

        return AuthenticationDTOGet.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getRefreshToken())
                .build();
    }
}
