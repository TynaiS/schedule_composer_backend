package com.example.schedule_composer.mappers;

import com.example.schedule_composer.dto.get.TeacherDTOGet;
import com.example.schedule_composer.dto.patch.TeacherDTOPatch;
import com.example.schedule_composer.dto.post.TeacherDTOPost;
import com.example.schedule_composer.entity.Teacher;

import java.util.List;

public interface TeacherMapper {
    TeacherDTOGet fromEntityToGet(Teacher teacher);
    List<TeacherDTOGet> fromEntityListToGetList(List<Teacher> teachers);
    Teacher fromPostToEntity(TeacherDTOPost teacherDTOPost);
    Teacher fromPatchToEntity(TeacherDTOPatch teacherDTOPatch, Teacher teacherToUpdate);
}
