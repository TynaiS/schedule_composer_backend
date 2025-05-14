package com.example.schedule_composer.entity;

import com.example.schedule_composer.utils.types.CoursePriority;
import com.example.schedule_composer.utils.types.RoomType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "setup")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Setup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teacher teacher;

    @Enumerated(EnumType.STRING)
    @Column(name = "course_priority")
    private CoursePriority coursePriority;

    @Column(name = "hours_a_week")
    private Integer hoursAWeek;

//    @Column(name = "hours_total")
//    private Integer hoursTotal;
//
//    @Column(name = "weeks_total")
//    private Integer weeksTotal;

    @Column(name = "hours_in_lab")
    private Integer hoursInLab;

    @Enumerated(EnumType.STRING)
    @Column(name = "preferred_room_type")
    private RoomType preferredRoomType;
}
