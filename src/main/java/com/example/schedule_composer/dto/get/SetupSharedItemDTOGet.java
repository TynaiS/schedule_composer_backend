package com.example.schedule_composer.dto.get;

import com.example.schedule_composer.utils.types.CoursePriority;
import com.example.schedule_composer.utils.types.RoomType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class SetupSharedItemDTOGet {

    private Long id;
    private SetupSharedSetDTOGet setupSharedSet;
    private List<GroupDTOGet> groups;
    private CourseDTOGet course;
    private TeacherDTOGet teacher;
    private CoursePriority coursePriority;
    private Integer hoursInLab;
    private RoomType preferredRoomType;
}
