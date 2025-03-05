package com.example.schedule_composer.entity;

import com.example.schedule_composer.utils.RoomType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rooms")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_num", nullable = false)
    private String roomNum;

    @Enumerated(EnumType.STRING)
    private RoomType type;
}
