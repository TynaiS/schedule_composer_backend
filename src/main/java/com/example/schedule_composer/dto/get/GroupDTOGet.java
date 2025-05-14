package com.example.schedule_composer.dto.get;

import com.example.schedule_composer.utils.types.GroupRoomSize;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GroupDTOGet {
    private Long id;
    private String name;
    private DepartmentDTOGet department;
    private GroupRoomSize size;
}

