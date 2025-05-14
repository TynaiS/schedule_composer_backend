package com.example.schedule_composer.dto.get;

import com.example.schedule_composer.utils.types.CoursePriority;
import com.example.schedule_composer.utils.types.RoomType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SetupDTOGet {

    private Long id;
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
