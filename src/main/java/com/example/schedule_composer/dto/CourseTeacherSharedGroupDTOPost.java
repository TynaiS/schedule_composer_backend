package com.example.schedule_composer.dto;

import com.example.schedule_composer.dto.get.CourseTeacherSharedDTOGet;
import com.example.schedule_composer.entity.Group;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CourseTeacherSharedGroupDTOPost {

    private Long id;
    private Long courseTeacherSharedId;
    private Group group;
}
