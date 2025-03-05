package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.CourseTeacherSharedGroupDTOGet;
import com.example.schedule_composer.dto.patch.CourseTeacherSharedGroupDTOPatch;
import com.example.schedule_composer.dto.post.CourseTeacherSharedGroupDTOPost;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseTeacherSharedGroupService implements CrudService<CourseTeacherSharedGroupDTOGet, CourseTeacherSharedGroupDTOPost, CourseTeacherSharedGroupDTOPatch, Long> {
    @Override
    public CourseTeacherSharedGroupDTOGet getById(Long aLong) {
        return null;
    }

    @Override
    public List<CourseTeacherSharedGroupDTOGet> getAll() {
        return null;
    }

    @Override
    public CourseTeacherSharedGroupDTOGet create(CourseTeacherSharedGroupDTOPost createDto) {
        return null;
    }

    @Override
    public CourseTeacherSharedGroupDTOGet update(Long aLong, CourseTeacherSharedGroupDTOPatch updateDto) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }
}
