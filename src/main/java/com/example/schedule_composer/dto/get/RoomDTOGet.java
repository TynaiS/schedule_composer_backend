package com.example.schedule_composer.dto.get;

import com.example.schedule_composer.utils.GroupRoomSize;
import com.example.schedule_composer.utils.RoomType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RoomDTOGet {

    private Long id;
    private String roomNum;
    private RoomType type;
    private GroupRoomSize size;

}
