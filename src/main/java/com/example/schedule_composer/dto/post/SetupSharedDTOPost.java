package com.example.schedule_composer.dto.post;

import com.example.schedule_composer.utils.types.CoursePriority;
import com.example.schedule_composer.utils.types.RoomType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class SetupSharedDTOPost {

    @NotNull(message = "Setup-Shared set id cannot be null")
    private Long setId;

    @NotNull(message = "Group ID's cannot be null")
    private List<Long> groupIds;

    @NotNull(message = "Course ID cannot be null")
    private Long courseId;

    @NotNull(message = "Teacher ID cannot be null")
    private Long teacherId;

    @NotNull(message = "Course priority cannot be null")
    private CoursePriority coursePriority;

    @NotNull(message = "Lab hours cannot be null")
    private Integer hoursInLab;

    @NotNull(message = "Preferred room type cannot be null")
    private RoomType preferredRoomType;

}
