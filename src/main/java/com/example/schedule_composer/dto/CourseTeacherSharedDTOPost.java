package com.example.schedule_composer.dto;

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

    private Long id;
    private String name;
    private Course course;
    private Teacher teacher;
    private CoursePriority coursePriority;
    private Integer hoursAWeek;
    private Integer hoursTotal;
    private Integer weeksTotal;
    private Integer hoursInLab;
    private RoomType preferredRoomType;

}
