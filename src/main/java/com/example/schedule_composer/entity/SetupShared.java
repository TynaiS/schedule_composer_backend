package com.example.schedule_composer.entity;

import com.example.schedule_composer.utils.CoursePriority;
import com.example.schedule_composer.utils.RoomType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "setup_shared")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SetupShared {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(
            name = "setup_shared_groups",
            joinColumns = @JoinColumn(name = "setup_shared_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private List<Group> groups;

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
