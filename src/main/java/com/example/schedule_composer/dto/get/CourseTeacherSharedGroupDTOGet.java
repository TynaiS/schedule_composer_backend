package com.example.schedule_composer.dto.get;

import com.example.schedule_composer.entity.CourseTeacherShared;
import com.example.schedule_composer.entity.Group;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CourseTeacherSharedGroupDTOGet {

    private Long id;
    private CourseTeacherSharedDTOGet courseTeacherShared;
    private Group group;
}
