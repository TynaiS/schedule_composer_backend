package com.example.schedule_composer.dto.post;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RefreshTokenDTOPost {

    @NotBlank
    private String refreshToken;
}
