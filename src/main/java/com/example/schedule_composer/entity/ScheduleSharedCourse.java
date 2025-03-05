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
@Table(name = "schedule_shared_courses")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleSharedCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_teacher_shared_id", referencedColumnName = "id")
    private CourseTeacherShared courseTeacherShared;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;

    @Column(nullable = false)
    private DayOfWeek day;

    @ManyToOne
    @JoinColumn(name = "start_time_slot_id", referencedColumnName = "id")
    private TimeSlot startTimeSlot;

    @ManyToOne
    @JoinColumn(name = "end_time_slot_id", referencedColumnName = "id")
    private TimeSlot endTimeSlot;

    @Enumerated(EnumType.STRING)
    private TeachingMode teachingMode;
}
