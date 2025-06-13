package com.example.schedule_composer.dto.patch;

import com.example.schedule_composer.utils.types.CoursePriority;
import com.example.schedule_composer.utils.types.RoomType;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class SetupSharedItemDTOPatch {
    private List<Long> groupIds;
    private Long courseId;
    private Long teacherId;
    private CoursePriority coursePriority;
    private Integer hoursInLab;
    private RoomType preferredRoomType;
}
