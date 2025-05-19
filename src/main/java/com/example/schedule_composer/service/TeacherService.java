package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.TeacherDTOGet;
import com.example.schedule_composer.dto.patch.TeacherDTOPatch;
import com.example.schedule_composer.dto.post.TeacherDTOPost;
import com.example.schedule_composer.entity.Teacher;

import java.util.List;

public interface TeacherService {

    TeacherDTOGet getById(Long id);
    TeacherDTOGet create(TeacherDTOPost createDto);
    TeacherDTOGet update(Long id, TeacherDTOPatch updateDto);
    void deleteById(Long id);
    List<TeacherDTOGet> getAll();


    Teacher getEntityById(Long id);
    Boolean checkIfExists(Long id);
    List<Teacher> getAllEntities();


    TeacherDTOGet getByIdForUserSchedule(Long userId, Long scheduleId, Long teacherId);
    List<TeacherDTOGet> getAllForUserSchedule(Long userId, Long scheduleId);
    TeacherDTOGet createForUserSchedule(Long userId, Long scheduleId, TeacherDTOPost request);
    TeacherDTOGet updateForUserSchedule(Long userId, Long scheduleId, Long teacherId, TeacherDTOPatch patchRequest);
    void deleteByIdForUserSchedule(Long userId, Long scheduleId, Long teacherId);


    Teacher getEntityByIdForUserSchedule(Long userId, Long scheduleId, Long teacherId);
    List<Teacher> getAllEntitiesForUserSchedule(Long userId, Long scheduleId);
}
