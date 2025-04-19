package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.DepartmentDTOGet;
import com.example.schedule_composer.dto.patch.DepartmentDTOPatch;
import com.example.schedule_composer.dto.post.DepartmentDTOPost;
import com.example.schedule_composer.entity.Department;

import java.util.List;

public interface DepartmentService {

    DepartmentDTOGet getById(Long id);

    Department getEntityById(Long id);

    Boolean checkIfExists(Long id);

    Department checkIfExistsAndGetEntity(Long id);

    List<Department> checkIfAllExistAndGetEntities(List<Long> groupIds);

    List<DepartmentDTOGet> getAll();

    List<Department> getAllEntities();

    DepartmentDTOGet create(DepartmentDTOPost createDto);

    DepartmentDTOGet update(Long id, DepartmentDTOPatch updateDto);

    void deleteById(Long id);
}
