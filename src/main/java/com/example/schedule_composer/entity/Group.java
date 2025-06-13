package com.example.schedule_composer.entity;

import com.example.schedule_composer.utils.types.GroupRoomSize;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "_group",
    uniqueConstraints = @UniqueConstraint(name = "unique_group_name", columnNames = {"schedule_id", "name"})
)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    @Enumerated(EnumType.STRING)
    private GroupRoomSize size;

    @PrePersist
    @PreUpdate
    public void trimName() {
        if (name != null) {
            name = name.trim();
        }
    }
}
