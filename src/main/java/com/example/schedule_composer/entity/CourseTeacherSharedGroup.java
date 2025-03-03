package com.example.schedule_composer.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "course_teacher_shared_groups")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseTeacherSharedGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_teacher_shared_id", referencedColumnName = "id")
    private CourseTeacherShared courseTeacherShared;

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private Group group;
}
