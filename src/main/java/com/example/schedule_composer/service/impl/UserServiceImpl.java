package com.example.schedule_composer.service.impl;

import com.example.schedule_composer.dto.post.ChangePasswordDTOPost;
import com.example.schedule_composer.entity.User;
import com.example.schedule_composer.exception.IncorrectPasswordException;
import com.example.schedule_composer.repository.UserRepository;
import com.example.schedule_composer.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getByEmail(String email) {
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
    public User getByVerificationCode(String token) {
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
        User user = getByEmail(email);

        if (!passwordEncoder.matches(dto.getOldPassword(), user.getPassword())) {
            throw new IncorrectPasswordException("Old password is incorrect");
        }

        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        userRepository.save(user);

        return "Password changed successfully.";
    }

}
