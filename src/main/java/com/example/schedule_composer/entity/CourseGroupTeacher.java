package com.example.schedule_composer.entity;

import com.example.schedule_composer.utils.CourseType;
import com.example.schedule_composer.utils.RoomType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseGroupTeacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id", nullable = false)
    private Group group;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id", nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id", nullable = false)
    private Teacher teacher;


    @Column(name = "hours_a_week")
    private Integer hoursAWeek;

    @Column(name = "hours_total")
    private Integer hoursTotal;

    @Enumerated(EnumType.STRING)
    private CourseType type;

    @Column(name = "required_room_type")
    private RoomType requiredRoomType;

}
