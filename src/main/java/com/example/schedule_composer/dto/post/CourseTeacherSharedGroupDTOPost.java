package com.example.schedule_composer.dto.post;

import com.example.schedule_composer.dto.get.CourseTeacherSharedDTOGet;
import com.example.schedule_composer.entity.Group;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CourseTeacherSharedGroupDTOPost {

    private Long courseTeacherSharedId;
    private Long groupId;
}
