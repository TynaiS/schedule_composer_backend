package com.example.schedule_composer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
    name = "teacher",
    uniqueConstraints = @UniqueConstraint(name = "unique_teacher_name", columnNames = {"schedule_id", "name"})
)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    private String name;
    private Integer dailyHours;
    private Integer weeklyHours;

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public static class TeacherBuilder {

        public TeacherBuilder name(String name) {
            this.name = (name == null) ? null : name.trim();
            return this;
        }
    }
}
