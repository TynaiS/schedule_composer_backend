package com.example.schedule_composer.dto.get;

import com.example.schedule_composer.utils.types.GroupRoomSize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GroupDTOGet {
    private Long id;
    private Long scheduleId;
    private String name;
    private DepartmentDTOGet department;
    private GroupRoomSize size;
}

