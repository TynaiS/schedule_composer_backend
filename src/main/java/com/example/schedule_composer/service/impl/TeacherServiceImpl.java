package com.example.schedule_composer.service.impl;

import com.example.schedule_composer.dto.get.TeacherDTOGet;
import com.example.schedule_composer.dto.patch.TeacherDTOPatch;
import com.example.schedule_composer.dto.post.TeacherDTOPost;
import com.example.schedule_composer.entity.Schedule;
import com.example.schedule_composer.entity.Teacher;
import com.example.schedule_composer.mappers.TeacherMapper;
import com.example.schedule_composer.repository.TeacherRepository;
import com.example.schedule_composer.service.ScheduleService;
import com.example.schedule_composer.service.TeacherService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;
    private final ScheduleService scheduleService;

    @Override
    public TeacherDTOGet getById(Long id) {
        return teacherMapper.fromEntityToGet(getEntityById(id));
    }

    @Override
    public TeacherDTOGet create(TeacherDTOPost createDto) {
        Teacher savedEntity = teacherRepository.save(teacherMapper.fromPostToEntity(createDto));
        return teacherMapper.fromEntityToGet(savedEntity);
    }

    @Override
    public TeacherDTOGet update(Long id, TeacherDTOPatch updateDto) {
        Teacher existing = getEntityById(id);
        Teacher updatedEntity = teacherRepository.save(teacherMapper.fromPatchToEntity(updateDto, existing));
        return teacherMapper.fromEntityToGet(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if (checkIfExists(id)) teacherRepository.deleteById(id);
    }

    @Override
    public List<TeacherDTOGet> getAll() {
        List<Teacher> entities = teacherRepository.findAll();
        return teacherMapper.fromEntityListToGetList(entities);
    }

    @Override
    public Teacher getEntityById(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found with id: " + id));
    }

    @Override
    public Boolean checkIfExists(Long id) {
        if (!teacherRepository.existsById(id)) {
            throw new EntityNotFoundException("Teacher not found with id: " + id);
        }
        return true;
    }

    @Override
    public List<Teacher> getAllEntities() {
        return teacherRepository.findAll();
    }

    @Override
    public TeacherDTOGet getByIdForUser(Long userId, Long teacherId) {
        return teacherMapper.fromEntityToGet(getEntityByIdForUser(userId, teacherId));
    }

    @Override
    public List<TeacherDTOGet> getAllForUserSchedule(Long userId, Long scheduleId) {
        return teacherMapper.fromEntityListToGetList(getAllEntitiesForUserSchedule(userId, scheduleId));
    }

    @Override
    public TeacherDTOGet createForUserSchedule(Long userId, Long scheduleId, TeacherDTOPost request) {
        Schedule schedule = scheduleService.getEntityByIdForUser(userId, scheduleId);

        Teacher teacher = teacherMapper.fromPostToEntity(request);
        teacher.setSchedule(schedule);
        Teacher saved = teacherRepository.save(teacher);
        return teacherMapper.fromEntityToGet(saved);
    }

    @Override
    public TeacherDTOGet updateForUser(Long userId, Long teacherId, TeacherDTOPatch patchRequest) {
        Teacher teacher = getEntityByIdForUser(userId, teacherId);

        teacher = teacherMapper.fromPatchToEntity(patchRequest, teacher);
        Teacher updated = teacherRepository.save(teacher);
        return teacherMapper.fromEntityToGet(updated);
    }

    @Override
    public void deleteByIdForUser(Long userId, Long teacherId) {
        Teacher teacher = getEntityByIdForUser(userId, teacherId);

        teacherRepository.delete(teacher);
    }

    @Override
    public Teacher getEntityByIdForUser(Long userId, Long teacherId) { //
        Teacher teacher = getEntityById(teacherId);
        scheduleService.checkUserAccessToSchedule(teacher.getSchedule(), userId);
        return teacher;
    }

    @Override
    public List<Teacher> getAllEntitiesForUserSchedule(Long userId, Long scheduleId) {
        Schedule schedule = scheduleService.getEntityByIdForUser(userId, scheduleId);

        return teacherRepository.findAllByScheduleId(schedule.getId());
    }
}
