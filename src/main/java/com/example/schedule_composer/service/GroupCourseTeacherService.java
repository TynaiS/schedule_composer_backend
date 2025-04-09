package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.GroupCourseTeacherDTOGet;
import com.example.schedule_composer.dto.patch.GroupCourseTeacherDTOPatch;
import com.example.schedule_composer.dto.post.GroupCourseTeacherDTOPost;
import com.example.schedule_composer.entity.GroupCourseTeacher;


import java.util.List;

public interface GroupCourseTeacherService {

    GroupCourseTeacherDTOGet getById(Long id);

    GroupCourseTeacher getEntityById(Long id);

    Boolean checkIfExists(Long id);

    List<GroupCourseTeacherDTOGet> getAll();

    GroupCourseTeacherDTOGet create(GroupCourseTeacherDTOPost createDto);

    GroupCourseTeacherDTOGet update(Long id, GroupCourseTeacherDTOPatch updateDto);

    void deleteById(Long id);
}
