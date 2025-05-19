package com.example.schedule_composer.service.impl;

import com.example.schedule_composer.dto.get.SetupSharedItemDTOGet;
import com.example.schedule_composer.dto.patch.SetupSharedItemDTOPatch;
import com.example.schedule_composer.dto.post.SetupSharedItemDTOPost;
import com.example.schedule_composer.entity.*;
import com.example.schedule_composer.mappers.SetupSharedItemMapper;
import com.example.schedule_composer.repository.SetupSharedItemRepository;
import com.example.schedule_composer.service.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SetupSharedItemServiceImpl implements SetupSharedItemService {

    private final SetupSharedItemRepository setupSharedItemRepository;
    private final SetupSharedItemMapper setupSharedItemMapper;
    private final SetupSharedSetService setupSharedSetService;
    private final GroupService groupService;
    private final CourseService courseService;
    private final TeacherService teacherService;
    private final ScheduleVersionService scheduleVersionService;

    @Override
    public SetupSharedItemDTOGet getById(Long id) {
        return setupSharedItemMapper.fromEntityToGet(getEntityById(id));
    }

    @Override
    public SetupSharedItemDTOGet create(SetupSharedItemDTOPost createDto) {
        SetupSharedItem savedEntity = setupSharedItemRepository.save(setupSharedItemMapper.fromPostToEntity(createDto));
        return setupSharedItemMapper.fromEntityToGet(savedEntity);
    }

    @Override
    public SetupSharedItemDTOGet update(Long setupSharedItemId, SetupSharedItemDTOPatch updateDto) {
        SetupSharedItem existing = getEntityById(setupSharedItemId);
        SetupSharedItem updatedEntity = setupSharedItemRepository.save(setupSharedItemMapper.fromPatchToEntity(updateDto, existing));
        return setupSharedItemMapper.fromEntityToGet(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if(checkIfExists(id)) setupSharedItemRepository.deleteById(id);
    }

    @Override
    public List<SetupSharedItemDTOGet> getAll() {
        List<SetupSharedItem> entities = setupSharedItemRepository.findAll();

        return setupSharedItemMapper.fromEntityListToGetList(entities);
    }

    @Override
    public List<SetupSharedItemDTOGet> getAllByGroupId(Long groupId) {
        List<SetupSharedItem> entities = setupSharedItemRepository.findByGroupsId(groupId);

        return setupSharedItemMapper.fromEntityListToGetList(entities);
    }

    @Override
    public SetupSharedItem getEntityById(Long id) {
        SetupSharedItem entity = setupSharedItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SetupSharedItem not found with id: " + id));
        return entity;
    }

    @Override
    public Boolean checkIfExists(Long id) {
        if (!setupSharedItemRepository.existsById(id)) {
            throw new EntityNotFoundException("SetupSharedItem not found with id: " + id);
        }
        return true;
    }

    @Override
    public List<SetupSharedItem> getAllEntities() {
        return setupSharedItemRepository.findAll();
    }

    @Override
    public SetupSharedItemDTOGet getByIdForUserScheduleVersion(Long userId, Long scheduleId, Long scheduleVersionId, Long setupSharedItemId) {
        return setupSharedItemMapper.fromEntityToGet(getEntityByIdForUserScheduleVersion(userId, scheduleId, scheduleVersionId, setupSharedItemId));

    }

    @Override
    public SetupSharedItemDTOGet getByIdForUserSetupSharedSet(Long userId, Long scheduleId, Long scheduleVersionId, Long setupSharedSetId, Long setupSharedItemId) {
        return setupSharedItemMapper.fromEntityToGet(getEntityByIdForUserSetupSharedSet(userId, scheduleId, scheduleVersionId, setupSharedSetId, setupSharedItemId));
    }

    @Override
    public List<SetupSharedItemDTOGet> getAllForUserSetupSharedSet(Long userId, Long scheduleId, Long scheduleVersionId, Long setupSharedSetId) {
        return setupSharedItemMapper.fromEntityListToGetList(getAllEntitiesForUserSetupSharedSet(userId, scheduleId, scheduleVersionId, setupSharedSetId));
    }

    @Override
    public List<SetupSharedItemDTOGet> getAllByGroupIdForUserSetupSharedSet(Long userId, Long scheduleId, Long scheduleVersionId, Long setupSharedSetId, Long groupId) {
        return setupSharedItemMapper.fromEntityListToGetList(getAllEntitiesByGroupIdForUserSetupSharedSet(userId, scheduleId, scheduleVersionId, setupSharedSetId, groupId));
    }

    @Override
    public SetupSharedItemDTOGet createForUserSetupSharedSet(Long userId, Long scheduleId, Long scheduleVersionId, Long setupSharedSetId, SetupSharedItemDTOPost request) {
        SetupSharedSet setupSharedSet = setupSharedSetService.getEntityByIdForUserScheduleVersion(userId, scheduleId, scheduleVersionId, setupSharedSetId);

        SetupSharedItem setupSharedItem = setupSharedItemMapper.fromPostToEntity(request);

        List<Group> groups = groupService.checkIfAllExistAndGetEntitiesForUserSchedule(userId, scheduleId, request.getGroupIds());
        Course course = courseService.getEntityByIdForUserSchedule(userId, scheduleId, request.getCourseId());
        Teacher teacher = teacherService.getEntityByIdForUserSchedule(userId, scheduleId, request.getTeacherId());

        setupSharedItem.setSetupSharedSet(setupSharedSet);
        setupSharedItem.setGroups(groups);
        setupSharedItem.setCourse(course);
        setupSharedItem.setTeacher(teacher);

        return setupSharedItemMapper.fromEntityToGet(setupSharedItemRepository.save(setupSharedItem));
    }

    @Override
    public SetupSharedItemDTOGet updateForUserSetupSharedSet(Long userId, Long scheduleId, Long scheduleVersionId, Long setupSharedSetId, Long setupSharedItemId, SetupSharedItemDTOPatch patchRequest) {
        SetupSharedItem setupSharedItem = getEntityByIdForUserSetupSharedSet(userId, scheduleId, scheduleVersionId, setupSharedSetId, setupSharedItemId);

        setupSharedItem = setupSharedItemMapper.fromPatchToEntity(patchRequest, setupSharedItem);

        if(patchRequest.getGroupIds() != null){
            List<Group> groups = groupService.checkIfAllExistAndGetEntities(patchRequest.getGroupIds());
            setupSharedItem.setGroups(groups);
        }

        if(patchRequest.getCourseId() != null){
            Course course = courseService.getEntityById(patchRequest.getCourseId());
            setupSharedItem.setCourse(course);
        }

        if(patchRequest.getTeacherId() != null){
            Teacher teacher = teacherService.getEntityById(patchRequest.getTeacherId());
            setupSharedItem.setTeacher(teacher);
        }

        return setupSharedItemMapper.fromEntityToGet(setupSharedItemRepository.save(setupSharedItem));
    }

    @Override
    public void deleteByIdForUserSetupSharedSet(Long userId, Long scheduleId, Long scheduleVersionId, Long setupSharedSetId, Long setupSharedItemId) {
        SetupSharedItem setupSharedItem = getEntityByIdForUserSetupSharedSet(userId, scheduleId, scheduleVersionId, setupSharedSetId, setupSharedItemId);

        setupSharedItemRepository.delete(setupSharedItem);
    }

    @Override
    public SetupSharedItem getEntityByIdForUserScheduleVersion(Long userId, Long scheduleId, Long scheduleVersionId, Long setupSharedItemId) {
        ScheduleVersion scheduleVersion = scheduleVersionService.getEntityByIdForUserSchedule(userId, scheduleId, scheduleVersionId);

        SetupSharedItem setupSharedItem = getEntityById(setupSharedItemId);
        scheduleVersionService.checkScheduleVersionId(scheduleVersion, setupSharedItem.getSetupSharedSet().getScheduleVersion().getId(), "SetupSharedItem");
        return setupSharedItem;
    }


    @Override
    public SetupSharedItem getEntityByIdForUserSetupSharedSet(Long userId, Long scheduleId, Long scheduleVersionId, Long setupSharedSetId, Long setupSharedItemId) {
        SetupSharedSet setupSharedSet = setupSharedSetService.getEntityByIdForUserScheduleVersion(userId, scheduleId, scheduleVersionId, setupSharedSetId);

        SetupSharedItem setupSharedItem = getEntityById(setupSharedItemId);
        setupSharedSetService.checkSetupSharedSetId(setupSharedSet, setupSharedItem.getSetupSharedSet().getId(), "SetupSharedItem");
        return setupSharedItem;
    }

    @Override
    public List<SetupSharedItem> getAllEntitiesForUserSetupSharedSet(Long userId, Long scheduleId, Long scheduleVersionId, Long setupSharedSetId) {
        SetupSharedSet setupSharedSet = setupSharedSetService.getEntityByIdForUserScheduleVersion(userId, scheduleId, scheduleVersionId, setupSharedSetId);

        return setupSharedItemRepository.findBySetupSharedSetId(setupSharedSet.getId());
    }

    @Override
    public List<SetupSharedItem> getAllEntitiesByGroupIdForUserSetupSharedSet(Long userId, Long scheduleId, Long scheduleVersionId, Long setupSharedSetId, Long groupId) {
        SetupSharedSet setupSharedSet = setupSharedSetService.getEntityByIdForUserScheduleVersion(userId, scheduleId, scheduleVersionId, setupSharedSetId);
        Group group = groupService.getEntityByIdForUserSchedule(userId, scheduleId, groupId);

        return setupSharedItemRepository.findByGroupsIdAndSetupSharedSetId(group.getId(), setupSharedSet.getId());
    }

    //  ----  Shared groups ----
//
//    @Override
//    public GroupDTOGet getGroupById(Long setupSharedId, Long groupId) {
//        SetupSharedItem setupSharedItem = getEntityById(setupSharedId);
//        Group group = setupSharedItem.getGroups().stream()
//                .filter(gr -> gr.getId().equals(groupId))
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("There is no group with id " + groupId + " in setupItem-shared with id " + setupSharedId));
//        return groupService.getById(group.getId());
//    }
//
//    @Override
//    public List<GroupDTOGet> getAllGroups(Long setupSharedId) {
//        SetupSharedItem setupSharedItem = getEntityById(setupSharedId);
//        return groupMapper.fromEntityListToGetList(setupSharedItem.getGroups());
//    }
//
//    @Override
//    public GroupDTOGet addGroup(Long setupSharedId, Long groupId) {
//        SetupSharedItem setupSharedItem = getEntityById(setupSharedId);
//        boolean groupExists = setupSharedItem.getGroups().stream()
//                .anyMatch(gr -> gr.getId().equals(groupId));
//
//        if (groupExists) {
//            throw new IllegalArgumentException("Group with id " + groupId + " already exists in setupItem-shared with id " + setupSharedId);
//        }
//
//        Group group = groupService.getEntityById(groupId);
//        setupSharedItem.getGroups().add(group);
//        setupSharedItemRepository.save(setupSharedItem);
//
//        return groupMapper.fromEntityToGet(group);
//    }
//
//    @Override
//    public void deleteGroupById(Long setupSharedId, Long groupId) {
//        SetupSharedItem setupSharedItem = getEntityById(setupSharedId);
//        Group group = setupSharedItem.getGroups().stream()
//                .filter(gr -> gr.getId().equals(groupId))
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("There is no group with id " + groupId + " in setupItem-shared with id " + setupSharedId));
//
//        setupSharedItem.getGroups().remove(group);
//        setupSharedItemRepository.save(setupSharedItem);
//    }
}
