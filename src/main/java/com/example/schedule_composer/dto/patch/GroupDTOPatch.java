package com.example.schedule_composer.dto.patch;

import com.example.schedule_composer.utils.types.GroupRoomSize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GroupDTOPatch {
    private String name;
    private Long departmentId;
    private GroupRoomSize size;
}
