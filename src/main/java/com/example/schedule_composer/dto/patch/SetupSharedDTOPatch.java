package com.example.schedule_composer.dto.patch;

import com.example.schedule_composer.utils.types.CoursePriority;
import com.example.schedule_composer.utils.types.RoomType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SetupSharedDTOPatch {
    private Long setId;
    private List<Long> groupIds;
    private Long courseId;
    private Long teacherId;
    private CoursePriority coursePriority;
    private Integer hoursInLab;
    private RoomType preferredRoomType;
}
