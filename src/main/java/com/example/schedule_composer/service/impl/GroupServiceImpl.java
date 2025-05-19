package com.example.schedule_composer.service.impl;

import com.example.schedule_composer.dto.get.GroupDTOGet;
import com.example.schedule_composer.dto.patch.GroupDTOPatch;
import com.example.schedule_composer.dto.post.GroupDTOPost;
import com.example.schedule_composer.entity.Department;
import com.example.schedule_composer.entity.Group;
import com.example.schedule_composer.entity.Schedule;
import com.example.schedule_composer.mappers.GroupMapper;
import com.example.schedule_composer.repository.GroupRepository;
import com.example.schedule_composer.service.DepartmentService;
import com.example.schedule_composer.service.GroupService;
import com.example.schedule_composer.service.ScheduleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;
    private final ScheduleService scheduleService;
    private final DepartmentService departmentService;


    @Override
    public GroupDTOGet getById(Long id) {
        return groupMapper.fromEntityToGet(getEntityById(id));
    }

    @Override
    public GroupDTOGet create(GroupDTOPost createDto) {
        Group saved = groupRepository.save(groupMapper.fromPostToEntity(createDto));
        return groupMapper.fromEntityToGet(saved);
    }

    @Override
    public GroupDTOGet update(Long id, GroupDTOPatch updateDto) {
        Group existing = getEntityById(id);
        Group updated = groupMapper.fromPatchToEntity(updateDto, existing);
        updated = groupRepository.save(updated);
        return groupMapper.fromEntityToGet(updated);
    }

    @Override
    public void deleteById(Long id) {
        if (checkIfExists(id)) {
            groupRepository.deleteById(id);
        }
    }

    @Override
    public List<GroupDTOGet> getAll() {
        return groupMapper.fromEntityListToGetList(groupRepository.findAll());
    }

    @Override
    public Group getEntityById(Long id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Group not found with id: " + id));
    }

    @Override
    public Boolean checkIfExists(Long id) {
        if (!groupRepository.existsById(id)) {
            throw new EntityNotFoundException("Group not found with id: " + id);
        }
        return true;
    }

    @Override
    public List<Group> getAllEntities() {
        return groupRepository.findAll();
    }


    @Override
    public List<Group> checkIfAllExistAndGetEntities(List<Long> groupIds) {
        return groupIds.stream()
                .map(this::getEntityById)
                .collect(Collectors.toList());
    }

    @Override
    public GroupDTOGet getByIdForUserSchedule(Long userId, Long scheduleId, Long groupId) {
        return groupMapper.fromEntityToGet(getEntityByIdForUserSchedule(userId, scheduleId, groupId));
    }

    @Override
    public List<GroupDTOGet> getAllForUserSchedule(Long userId, Long scheduleId) {
        return groupMapper.fromEntityListToGetList(getAllEntitiesForUserSchedule(userId, scheduleId));
    }

    @Override
    public GroupDTOGet createForUserSchedule(Long userId, Long scheduleId, GroupDTOPost request) {
        Schedule schedule = scheduleService.getEntityByIdForUser(userId, scheduleId);

        Department department = departmentService.getEntityByIdForUserSchedule(userId, scheduleId, request.getDepartmentId());

        Group group = groupMapper.fromPostToEntity(request);
        group.setSchedule(schedule);
        group.setDepartment(department);
        Group saved = groupRepository.save(group);
        return groupMapper.fromEntityToGet(saved);
    }

    @Override
    public GroupDTOGet updateForUserSchedule(Long userId, Long scheduleId, Long groupId, GroupDTOPatch patchRequest) {
        Group group = getEntityByIdForUserSchedule(userId, scheduleId, groupId);

        group = groupMapper.fromPatchToEntity(patchRequest, group);
        if(patchRequest.getDepartmentId() != null) {
            Department department = departmentService.getEntityByIdForUserSchedule(userId, scheduleId, patchRequest.getDepartmentId());
            group.setDepartment(department);
        }
        Group updated = groupRepository.save(group);
        return groupMapper.fromEntityToGet(updated);
    }

    @Override
    public void deleteByIdForUserSchedule(Long userId, Long scheduleId, Long groupId) {
        Group group = getEntityByIdForUserSchedule(userId, scheduleId, groupId);
        groupRepository.delete(group);
    }

    @Override
    public Group getEntityByIdForUserSchedule(Long userId, Long scheduleId, Long groupId) {
        Schedule schedule = scheduleService.getEntityByIdForUser(userId, scheduleId);

        Group group = getEntityById(groupId);
        scheduleService.checkScheduleId(schedule, group.getSchedule().getId(), "Group");
        return group;
    }

    @Override
    public List<Group> getAllEntitiesForUserSchedule(Long userId, Long scheduleId) {
        Schedule schedule = scheduleService.getEntityByIdForUser(userId, scheduleId);

        return groupRepository.findAllByScheduleId(schedule.getId());
    }

    @Override
    public List<Group> checkIfAllExistAndGetEntitiesForUserSchedule(Long userId, Long scheduleId, List<Long> groupIds) {
        return groupIds.stream()
                .map(id -> getEntityByIdForUserSchedule(userId, scheduleId, id))
                .collect(Collectors.toList());
    }
}
