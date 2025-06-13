package com.example.schedule_composer.dto.post;

import com.example.schedule_composer.utils.types.GroupRoomSize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GroupDTOPost {

    @NotBlank(message = "Group name cannot be blank")
    private String name;

    @NotNull(message = "Department ID cannot be null")
    private Long departmentId;

    @NotNull(message = "Group size cannot be null")
    private GroupRoomSize size;


}
