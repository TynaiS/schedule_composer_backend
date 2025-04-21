package com.example.schedule_composer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "setup_shared_names")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SetupSharedName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
