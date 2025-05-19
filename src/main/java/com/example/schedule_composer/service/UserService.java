package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.post.ChangePasswordDTOPost;
import com.example.schedule_composer.entity.User;

import java.util.List;

public interface UserService {

    User getEntityById(Long id);
    User getEntityByEmail(String email);

    boolean checkIsExists(String email);

    List<User> checkIfAllExistAndGetEntities(List<String> usersEmail);

    User getEntityByVerificationCode(String code);

    void verifyEmail(User user);

    boolean isEmailVerified(String email);

    String changePassword(ChangePasswordDTOPost resetPasswordDTOPost, String email);
}
