package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.CourseTeacherSharedDTOGet;
import com.example.schedule_composer.dto.patch.CourseTeacherSharedDTOPatch;
import com.example.schedule_composer.dto.post.CourseTeacherSharedDTOPost;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseTeacherSharedService implements CrudService<CourseTeacherSharedDTOGet, CourseTeacherSharedDTOPost, CourseTeacherSharedDTOPatch, Long>{

    @Override
    public CourseTeacherSharedDTOGet getById(Long aLong) {
        return null;
    }

    @Override
    public List<CourseTeacherSharedDTOGet> getAll() {
        return null;
    }

    @Override
    public CourseTeacherSharedDTOGet create(CourseTeacherSharedDTOPost createDto) {
        return null;
    }

    @Override
    public CourseTeacherSharedDTOGet update(Long aLong, CourseTeacherSharedDTOPatch updateDto) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }
}
