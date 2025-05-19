package com.example.schedule_composer.dto.get;

import com.example.schedule_composer.utils.types.GroupRoomSize;
import com.example.schedule_composer.utils.types.RoomType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RoomDTOGet {

    private Long id;
    private Long scheduleId;
    private String roomNum;
    private RoomType type;
    private GroupRoomSize size;

}
