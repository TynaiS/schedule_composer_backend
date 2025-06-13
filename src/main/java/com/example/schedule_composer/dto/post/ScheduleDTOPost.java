package com.example.schedule_composer.dto.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ScheduleDTOPost {

    @NotBlank(message = "Schedule name cannot be blank")
    private String name;

    @NotNull(message = "Schedule editor email cannot be null")
    private List<String> editorsEmails;
}
