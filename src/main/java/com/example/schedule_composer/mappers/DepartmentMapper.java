package com.example.schedule_composer.mappers;

import com.example.schedule_composer.dto.get.DepartmentDTOGet;
import com.example.schedule_composer.dto.patch.DepartmentDTOPatch;
import com.example.schedule_composer.dto.post.DepartmentDTOPost;
import com.example.schedule_composer.entity.Department;

import java.util.List;

public interface DepartmentMapper {
    DepartmentDTOGet fromEntityToGet(Department department);
    List<DepartmentDTOGet> fromEntityListToGetList(List<Department> departments);
    Department fromPostToEntity(DepartmentDTOPost departmentDTOPost);
    Department fromPatchToEntity(DepartmentDTOPatch departmentDTOPatch, Department departmentToUpdate);
}
