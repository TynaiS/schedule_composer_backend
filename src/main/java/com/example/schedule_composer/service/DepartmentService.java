package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.DepartmentDTOGet;
import com.example.schedule_composer.dto.patch.DepartmentDTOPatch;
import com.example.schedule_composer.dto.post.DepartmentDTOPost;
import com.example.schedule_composer.entity.Department;

import java.util.List;

public interface DepartmentService {

    DepartmentDTOGet getById(Long id);
    DepartmentDTOGet create(DepartmentDTOPost createDto);
    DepartmentDTOGet update(Long id, DepartmentDTOPatch updateDto);
    void deleteById(Long id);
    List<DepartmentDTOGet> getAll();


    Department getEntityById(Long id);
    Boolean checkIfExists(Long id);
    Department checkIfExistsAndGetEntity(Long id);
    List<Department> checkIfAllExistAndGetEntities(List<Long> groupIds);
    List<Department> getAllEntities();


    DepartmentDTOGet getByIdForUserSchedule(Long userId, Long scheduleId, Long departmentId);
    List<DepartmentDTOGet> getAllForUserSchedule(Long userId, Long scheduleId);
    DepartmentDTOGet createForUserSchedule(Long userId, Long scheduleId, DepartmentDTOPost request);
    DepartmentDTOGet updateForUserSchedule(Long userId, Long scheduleId, Long departmentId, DepartmentDTOPatch patchRequest);
    void deleteByIdForUserSchedule(Long userId, Long scheduleId, Long departmentId);


    Department getEntityByIdForUserSchedule(Long userId, Long scheduleId, Long departmentId);
    List<Department> getAllEntitiesForUserSchedule(Long userId, Long scheduleId);
}
