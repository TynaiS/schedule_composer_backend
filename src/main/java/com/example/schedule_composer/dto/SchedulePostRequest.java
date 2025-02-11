package com.example.schedule_composer.dto;

import com.example.schedule_composer.entity.CourseGroupTeacher;
import com.example.schedule_composer.entity.Room;
import com.example.schedule_composer.utils.TeachingMode;
import jakarta.persistence.*;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
public class SchedulePostRequest {

    private Long id;
    private Long courseGroupTeacherId;
    private Long roomId;
    private DayOfWeek day;
    private LocalTime startTime;
    private LocalTime endTime;
    private TeachingMode teachingMode;
}
