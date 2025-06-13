package com.example.schedule_composer.dto.patch;

import com.example.schedule_composer.utils.types.CoursePriority;
import com.example.schedule_composer.utils.types.RoomType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SetupItemDTOPatch {

    private Long groupId;
    private Long courseId;
    private Long teacherId;
    private CoursePriority coursePriority;
    private Integer hoursAWeek;
//    private Integer hoursTotal;
//    private Integer weeksTotal;
    private Integer hoursInLab;
    private RoomType preferredRoomType;

}
