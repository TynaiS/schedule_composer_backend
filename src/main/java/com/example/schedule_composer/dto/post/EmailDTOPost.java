package com.example.schedule_composer.dto.post;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class EmailDTOPost {
    @Email(message = "Email should be valid")
    @NotBlank
    private String email;
}
