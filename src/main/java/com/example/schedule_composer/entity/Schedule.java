package com.example.schedule_composer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(
    name = "schedule",
    uniqueConstraints = @UniqueConstraint(name = "unique_schedule_name", columnNames = {"user_id", "name"})
)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

    @Column(nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "schedule_editor",
            joinColumns = @JoinColumn(name = "schedule_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"schedule_id", "user_id"})
    )
    private List<User> editors;

    @PrePersist
    @PreUpdate
    public void trimName() {
        if (name != null) {
            name = name.trim();
        }
    }

}

