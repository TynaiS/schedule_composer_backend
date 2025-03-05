package com.example.schedule_composer.dto.post;

import com.example.schedule_composer.utils.RoomType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RoomDTOPost {

    private String roomNum;
    private RoomType type;

}
