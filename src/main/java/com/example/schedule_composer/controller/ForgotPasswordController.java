package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.post.EmailDTOPost;
import com.example.schedule_composer.dto.post.ResetPasswordDTOPost;
import com.example.schedule_composer.dto.post.SetupSharedSetDTOPost;
import com.example.schedule_composer.dto.post.TimeSlotDTOPost;
import com.example.schedule_composer.repository.ForgotPasswordRepository;
import com.example.schedule_composer.repository.UserRepository;
import com.example.schedule_composer.service.EmailService;
import com.example.schedule_composer.service.ForgotPasswordService;
import com.example.schedule_composer.utils.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstants.FORGOT_PASSWORD)
@RequiredArgsConstructor
@Tag(name = "Forgot Password API", description = "Endpoints for resetting forgotten password")
public class ForgotPasswordController {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final ForgotPasswordRepository forgotPasswordRepository;
    private final PasswordEncoder passwordEncoder;
    private final ForgotPasswordService forgotPasswordService;

    @PostMapping("/send-verification")
    @Operation(summary = "Send verification code", description = "Send verification code to email, to verify user with forgotten password")
    public ResponseEntity<String> verifyEmail(@Valid @RequestBody EmailDTOPost email) {
        return ResponseEntity.ok(forgotPasswordService.sendVerifyEmail(email));
    }

    @PostMapping("/reset-password")
    @Operation(summary = "Reset password", description = "Reset password using verification code sent on email")
    public ResponseEntity<String> resetPassword(@Valid @RequestBody ResetPasswordDTOPost resetPasswordDTOPost) {
        return ResponseEntity.ok(forgotPasswordService.resetPassword(resetPasswordDTOPost));
    }
}
