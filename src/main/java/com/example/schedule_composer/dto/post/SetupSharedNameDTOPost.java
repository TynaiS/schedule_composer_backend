package com.example.schedule_composer.dto.post;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SetupSharedNameDTOPost {
    @NotBlank(message = "SetupSharedName name cannot be blank")
    private String name;
}
