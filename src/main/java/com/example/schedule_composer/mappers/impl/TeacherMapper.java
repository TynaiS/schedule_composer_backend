package com.example.schedule_composer.mappers.impl;

import com.example.schedule_composer.dto.get.TeacherDTOGet;
import com.example.schedule_composer.mappers.DTOMapper;
import com.example.schedule_composer.dto.patch.TeacherDTOPatch;
import com.example.schedule_composer.dto.post.TeacherDTOPost;
import com.example.schedule_composer.entity.Teacher;
import com.example.schedule_composer.repository.TeacherRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TeacherMapper implements DTOMapper<TeacherDTOGet, TeacherDTOPost, TeacherDTOPatch, Teacher, Long> {

    private final TeacherRepository teacherRepository;

    @Override
    public TeacherDTOGet fromEntityToGet(Teacher teacher) {
        TeacherDTOGet teacherGet = new TeacherDTOGet(teacher.getId(), teacher.getName(), teacher.getDailyHours(), teacher.getWeeklyHours());
        return teacherGet;
    }

    @Override
    public List<TeacherDTOGet> fromEntityListToGetList(List<Teacher> teachers) {
        return teachers.stream()
                .map(this::fromEntityToGet)
                .collect(Collectors.toList());
    }

    @Override
    public Teacher fromPostToEntity(TeacherDTOPost teacherDTOPost) {
        Teacher teacher = Teacher.builder()
                .name(teacherDTOPost.getName())
                .dailyHours(teacherDTOPost.getDailyHours())
                .weeklyHours(teacherDTOPost.getWeeklyHours())
                .build();
        return teacher;
    }

    @Override
    public Teacher fromPatchToEntity(TeacherDTOPatch teacherDTOPatch, Long teacherId) {

        Teacher existingTeacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found with id: " + teacherId));

        if (teacherDTOPatch.getName() != null){
            if(teacherDTOPatch.getName().isBlank()) {
                throw new IllegalArgumentException("Teacher name cannot be blank");
            }
            existingTeacher.setName(teacherDTOPatch.getName());
        }

        if (teacherDTOPatch.getDailyHours() != null) {
            existingTeacher.setDailyHours(teacherDTOPatch.getDailyHours());
        }

        if (teacherDTOPatch.getWeeklyHours() != null) {
            existingTeacher.setWeeklyHours(teacherDTOPatch.getWeeklyHours());
        }

        return existingTeacher;

    }
}
