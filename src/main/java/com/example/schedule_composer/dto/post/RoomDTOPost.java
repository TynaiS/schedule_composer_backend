package com.example.schedule_composer.dto.post;

import com.example.schedule_composer.utils.types.GroupRoomSize;
import com.example.schedule_composer.utils.types.RoomType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RoomDTOPost {


    @NotBlank(message = "Room number cannot be blank")
    private String roomNum;

    @NotNull(message = "Room type cannot be null")
    private RoomType type;

    @NotNull(message = "Room size cannot be null")
    private GroupRoomSize size;

}
