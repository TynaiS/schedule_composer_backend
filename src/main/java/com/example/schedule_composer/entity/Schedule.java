package com.example.schedule_composer.entity;

import com.example.schedule_composer.utils.TeachingMode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_group_teacher_id", referencedColumnName = "id", nullable = false)
    private CourseGroupTeacher courseGroupTeacher;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id", nullable = false)
    private Room room;

    private DayOfWeek day;
    private LocalTime startTime;
    private LocalTime endTime;

    @Enumerated(EnumType.STRING)
    private TeachingMode teachingMode;
}
