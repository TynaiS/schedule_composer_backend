package com.example.schedule_composer.dto.patch;

import com.example.schedule_composer.utils.CoursePriority;
import com.example.schedule_composer.utils.RoomType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SetupSharedDTOPatch {
    private String name;
    private Long courseId;
    private Long teacherId;
    private CoursePriority coursePriority;
    private Integer hoursAWeek;
    private Integer hoursTotal;
    private Integer weeksTotal;
    private Integer hoursInLab;
    private RoomType preferredRoomType;
}
