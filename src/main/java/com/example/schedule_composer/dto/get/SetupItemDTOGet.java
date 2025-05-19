package com.example.schedule_composer.dto.get;

import com.example.schedule_composer.utils.types.CoursePriority;
import com.example.schedule_composer.utils.types.RoomType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SetupItemDTOGet {

    private Long id;
    private Long scheduleVersionId;
    private GroupDTOGet group;
    private CourseDTOGet course;
    private TeacherDTOGet teacher;
    private CoursePriority coursePriority;
    private Integer hoursAWeek;
//    private Integer hoursTotal;
//    private Integer weeksTotal;
    private Integer hoursInLab;
    private RoomType preferredRoomType;
}
