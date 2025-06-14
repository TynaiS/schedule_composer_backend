package com.example.schedule_composer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
    name = "department",
    uniqueConstraints = @UniqueConstraint(name = "unique_department_name", columnNames = {"schedule_id", "name"})
)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    private String name;

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public static class DepartmentBuilder {
        public DepartmentBuilder name(String name) {
            this.name = (name == null) ? null : name.trim();
            return this;
        }
    }

}
