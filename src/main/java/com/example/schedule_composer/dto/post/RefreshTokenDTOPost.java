package com.example.schedule_composer.dto.post;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RefreshTokenDTOPost {

    @NotBlank
    private String refreshToken;
}
