package com.example.schedule_composer.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "shared_groups")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SharedGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "setup_shared_id", referencedColumnName = "id")
    private SetupShared setupShared;

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private Group group;
}
