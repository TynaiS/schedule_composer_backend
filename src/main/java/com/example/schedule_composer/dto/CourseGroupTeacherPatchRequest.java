package com.example.schedule_composer.dto;

import com.example.schedule_composer.utils.CourseType;
import com.example.schedule_composer.utils.RoomType;
import lombok.Data;

@Data
public class CourseGroupTeacherPatchRequest {

    private Long groupId;
    private Long courseId;
    private Long teacherId;
    private Integer hoursAWeek;
    private Integer hoursTotal;
    private CourseType type;
    private RoomType requiredRoomType;

}
