package com.example.schedule_composer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roomNum;
    private String type;

    public Long getId() {
        return id;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public String getType() {
        return type;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public void setType(String type) {
        this.type = type;
    }
}
