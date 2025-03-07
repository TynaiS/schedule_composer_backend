package com.example.schedule_composer.dto.post;

import com.example.schedule_composer.dto.get.CourseTeacherSharedDTOGet;
import com.example.schedule_composer.entity.Group;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CourseTeacherSharedGroupDTOPost {

    @NotNull(message = "Course-Teacher-Shared ID cannot be null")
    private Long courseTeacherSharedId;

    @NotNull(message = "Group ID cannot be null")
    private Long groupId;
}
