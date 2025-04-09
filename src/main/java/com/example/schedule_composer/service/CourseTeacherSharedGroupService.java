package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.CourseTeacherSharedGroupDTOGet;
import com.example.schedule_composer.dto.patch.CourseTeacherSharedGroupDTOPatch;
import com.example.schedule_composer.dto.post.CourseTeacherSharedGroupDTOPost;
import com.example.schedule_composer.entity.CourseTeacherSharedGroup;



import java.util.List;

public interface CourseTeacherSharedGroupService {

    CourseTeacherSharedGroupDTOGet getById(Long id);

    CourseTeacherSharedGroup getEntityById(Long id);

    Boolean checkIfExists(Long id);

    List<CourseTeacherSharedGroupDTOGet> getAll();

    CourseTeacherSharedGroupDTOGet create(CourseTeacherSharedGroupDTOPost createDto);

    CourseTeacherSharedGroupDTOGet update(Long id, CourseTeacherSharedGroupDTOPatch updateDto);

    void deleteById(Long id);
}
