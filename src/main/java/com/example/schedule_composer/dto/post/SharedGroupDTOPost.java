package com.example.schedule_composer.dto.post;

import com.example.schedule_composer.dto.get.SetupSharedDTOGet;
import com.example.schedule_composer.entity.Group;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SharedGroupDTOPost {

    @NotNull(message = "Setup-Shared ID cannot be null")
    private Long setupSharedId;

    @NotNull(message = "Group ID cannot be null")
    private Long groupId;
}
