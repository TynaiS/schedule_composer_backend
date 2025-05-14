package com.example.schedule_composer.dto.get;

import com.example.schedule_composer.utils.types.CoursePriority;
import com.example.schedule_composer.utils.types.RoomType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class SetupSharedDTOGet {

    private Long id;
    private SetupSharedSetDTOGet set;
    private List<GroupDTOGet> groups;
    private CourseDTOGet course;
    private TeacherDTOGet teacher;
    private CoursePriority coursePriority;
    private Integer hoursInLab;
    private RoomType preferredRoomType;
}
