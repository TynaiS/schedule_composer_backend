package com.example.schedule_composer.dto.patch;

import com.example.schedule_composer.utils.CoursePriority;
import com.example.schedule_composer.utils.RoomType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GroupCourseTeacherDTOPatch {

    private Long groupId;
    private Long courseId;
    private Long teacherId;
    private CoursePriority coursePriority;
    private Integer hoursAWeek;
    private Integer hoursTotal;
    private Integer weeksTotal;
    private Integer hoursInLab;
    private RoomType preferredRoomType;

}
