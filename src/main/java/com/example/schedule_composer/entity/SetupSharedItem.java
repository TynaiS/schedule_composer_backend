package com.example.schedule_composer.entity;

import com.example.schedule_composer.utils.types.CoursePriority;
import com.example.schedule_composer.utils.types.RoomType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "setup_shared_item")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SetupSharedItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "setup_shared_set_id", referencedColumnName = "id")
    private SetupSharedSet setupSharedSet;

    @ManyToMany
    @JoinTable(
            name = "setup_shared_item_group",
            joinColumns = @JoinColumn(name = "setup_shared_item_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"setup_shared_item_id", "group_id"})
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


    @Column(name = "hours_in_lab")
    private Integer hoursInLab;

    @Enumerated(EnumType.STRING)
    @Column(name = "preferred_room_type")
    private RoomType preferredRoomType;
}
