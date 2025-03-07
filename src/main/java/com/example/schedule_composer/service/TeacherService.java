package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.TeacherDTOGet;
import com.example.schedule_composer.dto.mappers.TeacherMapper;
import com.example.schedule_composer.dto.patch.TeacherDTOPatch;
import com.example.schedule_composer.dto.post.TeacherDTOPost;
import com.example.schedule_composer.entity.Teacher;
import com.example.schedule_composer.repository.TeacherRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherService implements CrudService<TeacherDTOGet, TeacherDTOPost, TeacherDTOPatch, Teacher, Long> {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository,TeacherMapper teacherMapper){
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

        return entities.stream()
                .map(teacherMapper::fromEntityToGet)
                .collect(Collectors.toList());
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
