package com.example.schedule_composer.service.impl;

import com.example.schedule_composer.dto.get.TimeSlotDTOGet;
import com.example.schedule_composer.dto.patch.TimeSlotDTOPatch;
import com.example.schedule_composer.dto.post.TimeSlotDTOPost;
import com.example.schedule_composer.entity.Group;
import com.example.schedule_composer.entity.Schedule;
import com.example.schedule_composer.entity.TimeSlot;
import com.example.schedule_composer.mappers.TimeSlotMapper;
import com.example.schedule_composer.repository.TimeSlotRepository;
import com.example.schedule_composer.service.ScheduleService;
import com.example.schedule_composer.service.TimeSlotService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TimeSlotServiceImpl implements TimeSlotService {

    private final TimeSlotRepository timeSlotRepository;
    private final TimeSlotMapper timeSlotMapper;
    private final ScheduleService scheduleService;

    @Override
    public TimeSlotDTOGet getById(Long id) {
        return timeSlotMapper.fromEntityToGet(getEntityById(id));
    }

    @Override
    public TimeSlot getEntityById(Long id) {
        return timeSlotRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TimeSlot not found with id: " + id));
    }

    @Override
    public Boolean checkIfExists(Long id) {
        if (!timeSlotRepository.existsById(id)) {
            throw new EntityNotFoundException("TimeSlot not found with id: " + id);
        }
        return true;
    }

    @Override
    public TimeSlot checkIfExistsAndGetEntity(Long id) {
        checkIfExists(id);
        return getEntityById(id);
    }

    @Override
    public List<TimeSlot> checkIfAllExistAndGetEntities(List<Long> timeSlotIds) {
        return timeSlotIds.stream()
                .map(this::checkIfExistsAndGetEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<TimeSlotDTOGet> getAll() {
        return timeSlotMapper.fromEntityListToGetList(timeSlotRepository.findAll());
    }

    @Override
    public List<TimeSlot> getAllEntities() {
        return timeSlotRepository.findAll();
    }

    @Override
    public TimeSlotDTOGet create(TimeSlotDTOPost createDto) {
        TimeSlot saved = timeSlotRepository.save(timeSlotMapper.fromPostToEntity(createDto));
        return timeSlotMapper.fromEntityToGet(saved);
    }

    @Override
    public TimeSlotDTOGet update(Long id, TimeSlotDTOPatch updateDto) {
        TimeSlot existing = getEntityById(id);
        TimeSlot updated = timeSlotMapper.fromPatchToEntity(updateDto, existing);
        updated = timeSlotRepository.save(updated);
        return timeSlotMapper.fromEntityToGet(updated);
    }

    @Override
    public void deleteById(Long id) {
        if (checkIfExists(id)) {
            timeSlotRepository.deleteById(id);
        }
    }

    @Override
    public TimeSlotDTOGet getByIdForUserSchedule(Long userId, Long scheduleId, Long timeSlotId) {
        return timeSlotMapper.fromEntityToGet(getEntityByIdForUserSchedule(userId, scheduleId, timeSlotId));
    }

    @Override
    public List<TimeSlotDTOGet> getAllForUserSchedule(Long userId, Long scheduleId) {
        return timeSlotMapper.fromEntityListToGetList(getAllEntitiesForUserSchedule(userId, scheduleId));
    }

    @Override
    public TimeSlotDTOGet createForUserSchedule(Long userId, Long scheduleId, TimeSlotDTOPost request) {
        Schedule schedule = scheduleService.getEntityByIdForUser(userId, scheduleId);

        TimeSlot timeSlot = timeSlotMapper.fromPostToEntity(request);
        timeSlot.setSchedule(schedule);
        TimeSlot saved = timeSlotRepository.save(timeSlot);
        return timeSlotMapper.fromEntityToGet(saved);
    }

    @Override
    public TimeSlotDTOGet updateForUserSchedule(Long userId, Long scheduleId, Long timeSlotId, TimeSlotDTOPatch request) {
        TimeSlot timeSlot = getEntityByIdForUserSchedule(userId, scheduleId, timeSlotId);

        timeSlot = timeSlotMapper.fromPatchToEntity(request, timeSlot);
        TimeSlot updated = timeSlotRepository.save(timeSlot);
        return timeSlotMapper.fromEntityToGet(updated);
    }

    @Override
    public void deleteByIdForUserSchedule(Long userId, Long scheduleId, Long timeSlotId) {
        TimeSlot timeSlot = getEntityByIdForUserSchedule(userId, scheduleId, timeSlotId);

        timeSlotRepository.delete(timeSlot);
    }

    @Override
    public TimeSlot getEntityByIdForUserSchedule(Long userId, Long scheduleId, Long timeSlotId) {
        Schedule schedule = scheduleService.getEntityByIdForUser(userId, scheduleId);

        TimeSlot timeSlot = getEntityById(timeSlotId);
        scheduleService.checkScheduleId(schedule, timeSlot.getSchedule().getId(), "TimeSlot");
        return timeSlot;
    }

    @Override
    public List<TimeSlot> getAllEntitiesForUserSchedule(Long userId, Long scheduleId) {
        Schedule schedule = scheduleService.getEntityByIdForUser(userId, scheduleId);

        return timeSlotRepository.findAllByScheduleId(schedule.getId());
    }

    @Override
    public List<TimeSlot> checkIfAllExistAndGetEntitiesForUserSchedule(Long userId, Long scheduleId, List<Long> timeSlotIds) {
        return timeSlotIds.stream()
                    .map(id -> getEntityByIdForUserSchedule(userId, scheduleId, id))
                    .collect(Collectors.toList());
    }
}
