package com.example.schedule_composer.service.impl;

import com.example.schedule_composer.dto.get.DepartmentDTOGet;
import com.example.schedule_composer.dto.patch.DepartmentDTOPatch;
import com.example.schedule_composer.dto.post.DepartmentDTOPost;
import com.example.schedule_composer.entity.Department;
import com.example.schedule_composer.entity.Department;
import com.example.schedule_composer.entity.Schedule;
import com.example.schedule_composer.mappers.DepartmentMapper;
import com.example.schedule_composer.repository.DepartmentRepository;
import com.example.schedule_composer.service.DepartmentService;
import com.example.schedule_composer.service.ScheduleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;
    private final ScheduleService scheduleService;

    @Override
    public DepartmentDTOGet getById(Long id) {
        return departmentMapper.fromEntityToGet(getEntityById(id));
    }

    @Override
    public DepartmentDTOGet create(DepartmentDTOPost createDto) {
        Department savedEntity = departmentRepository.save(departmentMapper.fromPostToEntity(createDto));
        return departmentMapper.fromEntityToGet(savedEntity);
    }

    @Override
    public DepartmentDTOGet update(Long departmentId, DepartmentDTOPatch updateDto) {
        Department existing = getEntityById(departmentId);
        Department updatedEntity = departmentRepository.save(departmentMapper.fromPatchToEntity(updateDto, existing));
        return departmentMapper.fromEntityToGet(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if(checkIfExists(id)) departmentRepository.deleteById(id);
    }

    @Override
    public List<DepartmentDTOGet> getAll() {
        List<Department> entities = departmentRepository.findAll();
        return departmentMapper.fromEntityListToGetList(entities);
    }

    @Override
    public Department getEntityById(Long id) {
        Department entity = departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department not found with id: " + id));
        return entity;
    }

    @Override
    public Boolean checkIfExists(Long id) {
        if (!departmentRepository.existsById(id)) {
            throw new EntityNotFoundException("Department not found with id: " + id);
        }
        return true;
    }

    @Override
    public Department checkIfExistsAndGetEntity(Long id) {
        if (!departmentRepository.existsById(id)) {
            throw new EntityNotFoundException("Department not found with id: " + id);
        }
        return getEntityById(id);
    }

    @Override
    public List<Department> checkIfAllExistAndGetEntities(List<Long> departmentIds) {
        return departmentIds.stream()
                .map(this::checkIfExistsAndGetEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Department> getAllEntities() {
        return departmentRepository.findAll();
    }

    @Override
    public DepartmentDTOGet getByIdForUserSchedule(Long userId, Long departmentId) {
        return departmentMapper.fromEntityToGet(getEntityByIdForUserSchedule(userId, departmentId));
    }

    @Override
    public List<DepartmentDTOGet> getAllForUserSchedule(Long userId, Long scheduleId) {
        return departmentMapper.fromEntityListToGetList(getAllEntitiesForUserSchedule(userId, scheduleId));
    }

    @Override
    public DepartmentDTOGet createForUserSchedule(Long userId, Long scheduleId, DepartmentDTOPost request) {
        Schedule schedule = scheduleService.getEntityByIdForUser(userId, scheduleId);

        Department department = departmentMapper.fromPostToEntity(request);
        department.setSchedule(schedule);
        Department saved = departmentRepository.save(department);
        return departmentMapper.fromEntityToGet(saved);
    }

    @Override
    public DepartmentDTOGet updateForUserSchedule(Long userId, Long departmentId, DepartmentDTOPatch patchRequest) {
        Department department = getEntityByIdForUserSchedule(userId, departmentId);

        department = departmentMapper.fromPatchToEntity(patchRequest, department);
        Department updatedDepartment = departmentRepository.save(department);
        return departmentMapper.fromEntityToGet(updatedDepartment);
    }

    @Override
    public void deleteByIdForUserSchedule(Long userId, Long departmentId) {
        Department department = getEntityByIdForUserSchedule(userId, departmentId);

        departmentRepository.delete(department);
    }

    @Override
    public Department getEntityByIdForUserSchedule(Long userId, Long departmentId) {
        Department department = getEntityById(departmentId);
        scheduleService.checkUserAccessToSchedule(department.getSchedule(), userId);
        return department;
    }

    @Override
    public List<Department> getAllEntitiesForUserSchedule(Long userId, Long scheduleId) {
        Schedule schedule = scheduleService.getEntityByIdForUser(userId, scheduleId);

        return departmentRepository.findAllByScheduleId(schedule.getId());
    }
}
