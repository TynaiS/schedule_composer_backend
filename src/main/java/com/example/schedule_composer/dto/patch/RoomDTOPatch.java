package com.example.schedule_composer.dto.patch;

import com.example.schedule_composer.utils.types.GroupRoomSize;
import com.example.schedule_composer.utils.types.RoomType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RoomDTOPatch {
    private String roomNum;
    private RoomType type;
    private GroupRoomSize size;
}
