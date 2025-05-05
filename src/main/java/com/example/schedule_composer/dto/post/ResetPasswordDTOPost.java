package com.example.schedule_composer.dto.post;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResetPasswordDTOPost {

    @Email(message = "Email should be valid")
    @NotBlank String email;

    @NotBlank
    private String verificationCode;

    @NotBlank
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String newPassword;
}