package com.example.schedule_composer.service.impl;

import com.example.schedule_composer.dto.get.TeacherDTOGet;
import com.example.schedule_composer.dto.mappers.impl.TeacherMapper;
import com.example.schedule_composer.dto.patch.TeacherDTOPatch;
import com.example.schedule_composer.dto.post.TeacherDTOPost;
import com.example.schedule_composer.entity.Teacher;
import com.example.schedule_composer.repository.TeacherRepository;
import com.example.schedule_composer.service.TeacherService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository,TeacherMapper teacherMapper){
        this.teacherRepository = teacherRepository;
        this.teacherMapper = teacherMapper;
    }

    @Override
    public TeacherDTOGet getById(Long id) {
        return teacherMapper.fromEntityToGet(getEntityById(id));
    }

    @Override
    public Teacher getEntityById(Long id) {
        Teacher entity = teacherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found with id: " + id));
        return entity;
    }

    @Override
    public Boolean checkIfExists(Long id) {
        if (!teacherRepository.existsById(id)) {
            throw new EntityNotFoundException("Teacher not found with id: " + id);
        }
        return true;
    }

    @Override
    public List<TeacherDTOGet> getAll() {
        List<Teacher> entities = teacherRepository.findAll();

        return teacherMapper.fromEntityListToGetList(entities);
    }

    @Override
    public List<Teacher> getAllEntities() {
        return teacherRepository.findAll();
    }

    @Override
    public TeacherDTOGet create(TeacherDTOPost createDto) {
        Teacher savedEntity = teacherRepository.save(teacherMapper.fromPostToEntity(createDto));
        return teacherMapper.fromEntityToGet(savedEntity);
    }

    @Override
    public TeacherDTOGet update(Long id, TeacherDTOPatch updateDto) {
        Teacher updatedEntity = teacherRepository.save(teacherMapper.fromPatchToEntity(updateDto, id));
        return teacherMapper.fromEntityToGet(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if(checkIfExists(id)) teacherRepository.deleteById(id);
    }
}
