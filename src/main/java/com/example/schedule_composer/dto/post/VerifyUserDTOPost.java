package com.example.schedule_composer.dto.post;

import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VerifyUserDTOPost {
    @Email(message = "Email should be valid")
    private String email;
    private String verificationCode;
}
