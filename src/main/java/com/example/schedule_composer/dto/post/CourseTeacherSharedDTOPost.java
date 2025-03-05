package com.example.schedule_composer.dto.post;

import com.example.schedule_composer.entity.Course;
import com.example.schedule_composer.entity.Teacher;
import com.example.schedule_composer.utils.CoursePriority;
import com.example.schedule_composer.utils.RoomType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CourseTeacherSharedDTOPost {

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
