package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.post.ChangePasswordDTOPost;
import com.example.schedule_composer.entity.User;

public interface UserService {
    User getByEmail(String email);

    boolean checkIsExists(String email);

    User getByVerificationCode(String token);

    void verifyEmail(User user);

    boolean isEmailVerified(String email);

    String changePassword(ChangePasswordDTOPost resetPasswordDTOPost, String email);
}
