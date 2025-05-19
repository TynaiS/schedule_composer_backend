package com.example.schedule_composer.service.impl;

import com.example.schedule_composer.dto.post.ChangePasswordDTOPost;
import com.example.schedule_composer.entity.User;
import com.example.schedule_composer.exception.IncorrectPasswordException;
import com.example.schedule_composer.repository.UserRepository;
import com.example.schedule_composer.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getEntityById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    @Override
    public User getEntityByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
    }

    @Override
    public boolean checkIsExists(String email) {
        if (!userRepository.existsByEmail(email)) {
            throw new EntityNotFoundException("User not found with email: " + email);
        }
        return true;
    }

    @Override
    public List<User> checkIfAllExistAndGetEntities(List<String> usersEmails) {
        return usersEmails.stream()
                .map(this::getEntityByEmail)
                .collect(Collectors.toList());
    }

    @Override
    public User getEntityByVerificationCode(String token) {
        return userRepository.findByVerificationCode(token)
                .orElseThrow(() -> new EntityNotFoundException("User not found with verification token: " + token));
    }

    @Override
    public void verifyEmail(User user) {
        user.setEmailVerified(true);
        user.setEnabled(true);
        userRepository.save(user);
    }

    @Override
    public boolean isEmailVerified(String email) {
        return userRepository.findByEmail(email)
                .map(User::isEmailVerified)
                .orElse(false);
    }

    @Override
    public String changePassword(ChangePasswordDTOPost dto, String email) {
        User user = getEntityByEmail(email);

        if (!passwordEncoder.matches(dto.getOldPassword(), user.getPassword())) {
            throw new IncorrectPasswordException("Old password is incorrect");
        }

        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        userRepository.save(user);

        return "Password changed successfully.";
    }

}
