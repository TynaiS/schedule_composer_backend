package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.CourseTeacherSharedDTOGet;
import com.example.schedule_composer.dto.patch.CourseTeacherSharedDTOPatch;
import com.example.schedule_composer.dto.post.CourseTeacherSharedDTOPost;
import com.example.schedule_composer.entity.CourseTeacherShared;

import java.util.List;

public interface CourseTeacherSharedService {

    CourseTeacherSharedDTOGet getById(Long id);

    CourseTeacherShared getEntityById(Long id);

    Boolean checkIfExists(Long id);

    List<CourseTeacherSharedDTOGet> getAll();

    CourseTeacherSharedDTOGet create(CourseTeacherSharedDTOPost createDto);

    CourseTeacherSharedDTOGet update(Long id, CourseTeacherSharedDTOPatch updateDto);

    void deleteById(Long id);
}
