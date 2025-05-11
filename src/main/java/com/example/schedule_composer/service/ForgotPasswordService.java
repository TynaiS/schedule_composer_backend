package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.post.EmailDTOPost;
import com.example.schedule_composer.dto.post.ResetPasswordDTOPost;
import com.example.schedule_composer.dto.post.SetupSharedSetDTOPost;
import com.example.schedule_composer.dto.post.TimeSlotDTOPost;
import com.example.schedule_composer.entity.ForgotPassword;
import com.example.schedule_composer.entity.User;

public interface ForgotPasswordService {

    ForgotPassword getByUser(User user);
    String sendVerifyEmail(EmailDTOPost email);

    String resetPassword(ResetPasswordDTOPost resetPasswordDTOPost);

    void sendVerificationEmail(User user, String verificationCode);
}
