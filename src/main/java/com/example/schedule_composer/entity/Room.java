package com.example.schedule_composer.entity;

import com.example.schedule_composer.utils.types.GroupRoomSize;
import com.example.schedule_composer.utils.types.RoomType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
    name = "room",
    uniqueConstraints = @UniqueConstraint(name = "unique_room_num", columnNames = {"schedule_id", "room_num"})
)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    @Column(name = "room_num", nullable = false)
    private String roomNum;

    @Enumerated(EnumType.STRING)
    private RoomType type;

    @Enumerated(EnumType.STRING)
    private GroupRoomSize size;

    public void setName(String roomNum) {
        this.roomNum = roomNum == null ? null : roomNum.trim();
    }

    public static class RoomBuilder {
        public RoomBuilder name(String roomNum) {
            this.roomNum = (roomNum == null) ? null : roomNum.trim();
            return this;
        }
    }
}
