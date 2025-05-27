package com.example.schedule_composer.service.impl;

import com.example.schedule_composer.dto.get.SetupItemDTOGet;
import com.example.schedule_composer.dto.patch.SetupItemDTOPatch;
import com.example.schedule_composer.dto.post.SetupItemDTOPost;
import com.example.schedule_composer.entity.*;
import com.example.schedule_composer.mappers.SetupItemMapper;
import com.example.schedule_composer.repository.SetupItemRepository;
import com.example.schedule_composer.service.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SetupItemServiceImpl implements SetupItemService {

    private final SetupItemRepository setupItemRepository;
    private final SetupItemMapper setupItemMapper;
    private final ScheduleVersionService scheduleVersionService;
    private final GroupService groupService;
    private final CourseService courseService;
    private final TeacherService teacherService;


    @Override
    public SetupItemDTOGet getById(Long id) {
        return setupItemMapper.fromEntityToGet(getEntityById(id));
    }

    @Override
    public SetupItemDTOGet create(SetupItemDTOPost createDto) {
        SetupItem savedEntity = setupItemRepository.save(setupItemMapper.fromPostToEntity(createDto));
        return setupItemMapper.fromEntityToGet(savedEntity);
    }

    @Override
    public SetupItemDTOGet update(Long setupItemId, SetupItemDTOPatch updateDto) {
        SetupItem existing = getEntityById(setupItemId);
        SetupItem updatedEntity = setupItemRepository.save(setupItemMapper.fromPatchToEntity(updateDto, existing));
        return setupItemMapper.fromEntityToGet(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if(checkIfExists(id)) setupItemRepository.deleteById(id);
    }

    @Override
    public List<SetupItemDTOGet> getAll() {
        List<SetupItem> entities = setupItemRepository.findAll();

        return setupItemMapper.fromEntityListToGetList(entities);
    }

    @Override
    public List<SetupItemDTOGet> getAllByGroupId(Long groupId) {
        List<SetupItem> entities = setupItemRepository.findAllByGroupId(groupId);

        return setupItemMapper.fromEntityListToGetList(entities);
    }

    @Override
    public SetupItem getEntityById(Long id) {
        SetupItem entity = setupItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SetupItem not found with id: " + id));
        return entity;
    }

    @Override
    public Boolean checkIfExists(Long id) {
        if (!setupItemRepository.existsById(id)) {
            throw new EntityNotFoundException("SetupItem not found with id: " + id);
        }
        return true;
    }

    @Override
    public List<SetupItem> getAllEntities() {
        return setupItemRepository.findAll();
    }

    @Override
    public SetupItemDTOGet getByIdForUser(Long userId, Long setupItemId) {
        return setupItemMapper.fromEntityToGet(getEntityByIdForUserScheduleVersion(userId, setupItemId));
    }

    @Override
    public List<SetupItemDTOGet> getAllForUserScheduleVersion(Long userId, Long scheduleVersionId) {
        return setupItemMapper.fromEntityListToGetList(getAllEntitiesForUserScheduleVersion(userId, scheduleVersionId));
    }

    @Override
    public List<SetupItemDTOGet> getAllByGroupIdForUserScheduleVersion(Long userId, Long scheduleVersionId, Long groupId) {
        return setupItemMapper.fromEntityListToGetList(getAllEntitiesByGroupIdForUserScheduleVersion(userId, scheduleVersionId, groupId));
    }

    @Override
    public SetupItemDTOGet createForUserScheduleVersion(Long userId, Long scheduleVersionId, SetupItemDTOPost request) {
        ScheduleVersion scheduleVersion = scheduleVersionService.getEntityByIdForUser(userId, scheduleVersionId);

        SetupItem setupItem = setupItemMapper.fromPostToEntity(request);

        Group group = groupService.getEntityByIdForUserSchedule(userId, request.getGroupId());
        Course course = courseService.getEntityByIdForUserSchedule(userId, request.getCourseId());
        Teacher teacher = teacherService.getEntityByIdForUser(userId, request.getTeacherId());

        setupItem.setScheduleVersion(scheduleVersion);
        setupItem.setGroup(group);
        setupItem.setCourse(course);
        setupItem.setTeacher(teacher);

        return setupItemMapper.fromEntityToGet(setupItemRepository.save(setupItem));
    }

    @Override
    public SetupItemDTOGet updateForUser(Long userId, Long setupItemId, SetupItemDTOPatch patchRequest) {
        SetupItem setupItem = getEntityByIdForUserScheduleVersion(userId, setupItemId);

        setupItem = setupItemMapper.fromPatchToEntity(patchRequest, setupItem);

        if(patchRequest.getGroupId() != null){
            Group group = groupService.getEntityByIdForUserSchedule(userId, patchRequest.getGroupId());
            setupItem.setGroup(group);
        }

        if(patchRequest.getCourseId() != null){
            Course course = courseService.getEntityByIdForUserSchedule(userId, patchRequest.getCourseId());
            setupItem.setCourse(course);
        }

        if(patchRequest.getTeacherId() != null){
            Teacher teacher = teacherService.getEntityByIdForUser(userId, patchRequest.getTeacherId());
            setupItem.setTeacher(teacher);
        }

        return setupItemMapper.fromEntityToGet(setupItemRepository.save(setupItem));
    }

    @Override
    public void deleteByIdForUser(Long userId, Long setupItemId) {
        SetupItem setupItem = getEntityByIdForUserScheduleVersion(userId, setupItemId);
        setupItemRepository.delete(setupItem);
    }



    @Override
    public SetupItem getEntityByIdForUserScheduleVersion(Long userId, Long setupItemId) {
        SetupItem setupItem = getEntityById(setupItemId);
        scheduleVersionService.checkUserAccessToScheduleVersion(setupItem.getScheduleVersion(), userId);
        return setupItem;
    }

    @Override
    public List<SetupItem> getAllEntitiesForUserScheduleVersion(Long userId, Long scheduleVersionId) {
        ScheduleVersion scheduleVersion = scheduleVersionService.getEntityByIdForUser(userId, scheduleVersionId);

        return setupItemRepository.findAllByScheduleVersionId(scheduleVersion.getId());
    }

    @Override
    public List<SetupItem> getAllEntitiesByGroupIdForUserScheduleVersion(Long userId, Long scheduleVersionId, Long groupId) {
        ScheduleVersion scheduleVersion = scheduleVersionService.getEntityByIdForUser(userId, scheduleVersionId);
        Group group = groupService.getEntityByIdForUserSchedule(userId, groupId);

        return setupItemRepository.findAllByGroupIdAndScheduleVersionId(group.getId(), scheduleVersion.getId());
    }
}
