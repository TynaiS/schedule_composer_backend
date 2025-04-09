package com.example.schedule_composer.entity;

import com.example.schedule_composer.utils.TeachingMode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.util.List;

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

    @ManyToMany
    @JoinTable(
            name = "time_slots",
            joinColumns = @JoinColumn(name = "schedule_id"),
            inverseJoinColumns = @JoinColumn(name = "time_slot_id")
    )
    private List<TimeSlot> timeSlots;

    @Enumerated(EnumType.STRING)
    private TeachingMode teachingMode;
}
