package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.TeacherDTOGet;
import com.example.schedule_composer.dto.patch.TeacherDTOPatch;
import com.example.schedule_composer.dto.post.TeacherDTOPost;
import com.example.schedule_composer.entity.Teacher;
import com.example.schedule_composer.repository.TeacherRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService implements CrudService<TeacherDTOGet, TeacherDTOPost, TeacherDTOPatch, Long>{
    @Override
    public TeacherDTOGet getById(Long aLong) {
        return null;
    }

    @Override
    public List<TeacherDTOGet> getAll() {
        return null;
    }

    @Override
    public TeacherDTOGet create(TeacherDTOPost createDto) {
        return null;
    }

    @Override
    public TeacherDTOGet update(Long aLong, TeacherDTOPatch updateDto) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }
}
