package com.example.schedule_composer.dto.patch;

import com.example.schedule_composer.utils.types.GroupRoomSize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class GroupDTOPatch {
    private String name;
    private Long departmentId;
    private GroupRoomSize size;
}
