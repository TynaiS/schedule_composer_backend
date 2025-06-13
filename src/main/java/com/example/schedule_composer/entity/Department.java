package com.example.schedule_composer.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

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

    @PrePersist
    @PreUpdate
    public void trimName() {
        if (name != null) {
            name = name.trim();
        }
    }

}
