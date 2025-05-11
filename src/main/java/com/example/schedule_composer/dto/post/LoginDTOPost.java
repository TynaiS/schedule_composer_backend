package com.example.schedule_composer.dto.post;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTOPost {
    @Email(message = "Email should be valid")
    private String email;
    private String password;

}
