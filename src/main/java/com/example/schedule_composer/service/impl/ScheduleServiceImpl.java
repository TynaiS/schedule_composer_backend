package com.example.schedule_composer.service.impl;

import com.example.schedule_composer.dto.get.ScheduleDTOGet;
import com.example.schedule_composer.dto.patch.ScheduleDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleDTOPost;
import com.example.schedule_composer.entity.Schedule;
import com.example.schedule_composer.entity.User;
import com.example.schedule_composer.mappers.ScheduleMapper;
import com.example.schedule_composer.repository.ScheduleRepository;
import com.example.schedule_composer.service.ScheduleService;
import com.example.schedule_composer.service.UserService;
import com.example.schedule_composer.utils.types.UserRole;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;
    private final UserService userService;

    @Override
    public ScheduleDTOGet getById(Long id) {
        return scheduleMapper.fromEntityToGet(getEntityById(id));
    }

    @Override
    public ScheduleDTOGet create(ScheduleDTOPost createDto) {
        Schedule savedEntity = scheduleRepository.save(scheduleMapper.fromPostToEntity(createDto));
        return scheduleMapper.fromEntityToGet(savedEntity);
    }

    @Override
    public ScheduleDTOGet create(Schedule createEntity) {
        Schedule savedEntity = scheduleRepository.save(createEntity);
        return scheduleMapper.fromEntityToGet(savedEntity);
    }

    @Override
    public ScheduleDTOGet update(Long scheduleId, ScheduleDTOPatch updateDto) {
        Schedule existing = getEntityById(scheduleId);
        Schedule updatedEntity = scheduleRepository.save(scheduleMapper.fromPatchToEntity(updateDto, existing));
        return scheduleMapper.fromEntityToGet(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if (checkIfExists(id)) scheduleRepository.deleteById(id);
    }

    @Override
    public List<ScheduleDTOGet> getAll() {
        List<Schedule> entities = scheduleRepository.findAll();
        return scheduleMapper.fromEntityListToGetList(entities);
    }

    @Override
    public Schedule getEntityById(Long id) {
        Schedule entity = scheduleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Schedule not found with id: " + id));
        return entity;
    }

    @Override
    public Boolean checkIfExists(Long id) {
        if (!scheduleRepository.existsById(id)) {
            throw new EntityNotFoundException("Schedule not found with id: " + id);
        }
        return true;
    }

    @Override
    public List<Schedule> getAllEntities() {
        return scheduleRepository.findAll();
    }

    @Override
    public ScheduleDTOGet getByIdForUser(Long userId, Long scheduleId) {
        return scheduleMapper.fromEntityToGet(getEntityByIdForUser(userId, scheduleId));
    }

    @Override
    public List<ScheduleDTOGet> getAllForUserOwner(Long userId) {
        return scheduleMapper.fromEntityListToGetList(getAllEntitiesForUserOwner(userId));
    }

    @Override
    public List<ScheduleDTOGet> getAllForUserEditor(Long userId) {
        return scheduleMapper.fromEntityListToGetList(getAllEntitiesForUserEditor(userId));
    }

    @Override
    public ScheduleDTOGet createForUser(User user, ScheduleDTOPost postRequest) {
        List<User> editors = checkEditorsEmails(user.getId(), postRequest.getEditorsEmails());
        Schedule schedule = scheduleMapper.fromPostToEntity(postRequest);
        schedule.setOwner(user);
        schedule.setEditors(editors);
        return scheduleMapper.fromEntityToGet(scheduleRepository.save(schedule));
    }

    @Override
    public ScheduleDTOGet updateForUser(Long userId, Long scheduleId, ScheduleDTOPatch patchRequest) {
        Schedule schedule = getEntityById(scheduleId);
        checkUserAccessToSchedule(schedule, userId);
        Schedule mappedSchedule = scheduleMapper.fromPatchToEntity(patchRequest, schedule);
        List<String> editorsEmails = patchRequest.getEditorsEmails();

        if (editorsEmails != null) {
            List<User> editors = checkEditorsEmails(userId, patchRequest.getEditorsEmails());
            mappedSchedule.setEditors(editors);
        }

        return scheduleMapper.fromEntityToGet(mappedSchedule);
    }

    @Override
    public void deleteByIdForUser(Long userId, Long scheduleId) {
        Schedule schedule = getEntityById(scheduleId);
        checkUserAccessToSchedule(schedule, userId);
        scheduleRepository.delete(schedule);
    }

    @Override
    public void checkUserAccessToSchedule(Schedule schedule, Long userId) {
        boolean hasAccess = schedule.getOwner().getId().equals(userId)
                || schedule.getEditors().stream().anyMatch(e -> e.getId().equals(userId));

        if (!hasAccess) {
            throw new AccessDeniedException("Access denied to Schedule");
        }
    }

    @Override
    public void checkScheduleId(Schedule schedule, Long scheduleId, String entityName) {
        if (!schedule.getId().equals(scheduleId)) {
            throw new IllegalArgumentException(entityName + " does not belong to the given Schedule");
        }
    }

    @Override
    public Schedule getEntityByIdForUser(Long userId, Long scheduleId) {
        Schedule schedule = getEntityById(scheduleId);
        checkUserAccessToSchedule(schedule, userId);
        return schedule;
    }

    @Override
    public List<Schedule> getAllEntitiesForUserOwner(Long userId) {
        List<Schedule> ownedSchedules = scheduleRepository.findAllByOwnerId(userId);
        return ownedSchedules;
    }

    @Override
    public List<Schedule> getAllEntitiesForUserEditor(Long userId) {
        List<Schedule> editorSchedules = scheduleRepository.findAllByEditors_Id(userId).stream()
                .filter(schedule -> !schedule.getOwner().getId().equals(userId))
                .collect(Collectors.toList());
        return editorSchedules;
    }

    private List<User> checkEditorsEmails(Long ownerId, List<String> editorsEmails) {
        List<User> editors = new ArrayList<>();
        if (editorsEmails != null && !editorsEmails.isEmpty()) {
            editors = editorsEmails.stream()
                    .map(email -> {
                        User user = userService.getEntityByEmail(email);

                        if (user.getRole() != UserRole.CREATOR) {
                            throw new IllegalArgumentException("User " + email + " does not have CREATOR role");
                        }

                        return user;
                    })
                    .collect(Collectors.toList());
        }

        editors.removeIf(editor -> editor.getId().equals(ownerId));

        return editors;
    }
}
