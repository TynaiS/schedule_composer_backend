package com.example.schedule_composer.dto.post;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VerifyUserDTOPost {
    @Email(message = "Email should be valid")
    private String email;
    private String verificationCode;
}
