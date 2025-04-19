package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.TeacherDTOGet;
import com.example.schedule_composer.dto.patch.TeacherDTOPatch;
import com.example.schedule_composer.dto.post.TeacherDTOPost;
import com.example.schedule_composer.entity.Teacher;

import java.util.List;

public interface TeacherService {

    TeacherDTOGet getById(Long id);

    Teacher getEntityById(Long id);

    Boolean checkIfExists(Long id);

    List<TeacherDTOGet> getAll();

    List<Teacher> getAllEntities();

    TeacherDTOGet create(TeacherDTOPost createDto);

    TeacherDTOGet update(Long id, TeacherDTOPatch updateDto);

    void deleteById(Long id);
}
