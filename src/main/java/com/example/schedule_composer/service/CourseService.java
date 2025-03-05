package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.CourseDTOGet;
import com.example.schedule_composer.dto.patch.CourseDTOPatch;
import com.example.schedule_composer.dto.post.CourseDTOPost;
import com.example.schedule_composer.entity.Course;
import com.example.schedule_composer.repository.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService implements CrudService<CourseDTOGet, CourseDTOPost, CourseDTOPatch, Long>{

    @Override
    public CourseDTOGet getById(Long aLong) {
        return null;
    }

    @Override
    public List<CourseDTOGet> getAll() {
        return null;
    }

    @Override
    public CourseDTOGet create(CourseDTOPost createDto) {
        return null;
    }

    @Override
    public CourseDTOGet update(Long aLong, CourseDTOPatch updateDto) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }
}
