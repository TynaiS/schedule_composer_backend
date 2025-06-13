package com.example.schedule_composer.dto.post;

import jakarta.validation.constraints.Email;
import lombok.*;

@Getter
@Builder
public class LoginDTOPost {
    @Email(message = "Email should be valid")
    private String email;
    private String password;

}
