package com.example.schedule_composer.dto.post;

import com.example.schedule_composer.utils.CoursePriority;
import com.example.schedule_composer.utils.RoomType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class SetupSharedDTOPost {

    @NotNull(message = "Setup-Shared name id cannot be null")
    private Long nameId;

    @NotNull(message = "Group ID's cannot be null")
    private List<Long> groupIds;

    @NotNull(message = "Course ID cannot be null")
    private Long courseId;

    @NotNull(message = "Teacher ID cannot be null")
    private Long teacherId;

    @NotNull(message = "Course priority cannot be null")
    private CoursePriority coursePriority;

    @NotNull(message = "Hours per week cannot be null")
    private Integer hoursAWeek;

//    @NotNull(message = "Total hours cannot be null")
//    private Integer hoursTotal;

//    @NotNull(message = "Total weeks cannot be null")
//    private Integer weeksTotal;

    @NotNull(message = "Lab hours cannot be null")
    private Integer hoursInLab;

    @NotNull(message = "Preferred room type cannot be null")
    private RoomType preferredRoomType;

}
