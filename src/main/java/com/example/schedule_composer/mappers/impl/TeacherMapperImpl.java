package com.example.schedule_composer.mappers.impl;

import com.example.schedule_composer.dto.get.TeacherDTOGet;
import com.example.schedule_composer.dto.patch.TeacherDTOPatch;
import com.example.schedule_composer.dto.post.TeacherDTOPost;
import com.example.schedule_composer.entity.Teacher;
import com.example.schedule_composer.mappers.TeacherMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TeacherMapperImpl implements TeacherMapper {

    @Override
    public TeacherDTOGet fromEntityToGet(Teacher teacher) {
        TeacherDTOGet teacherGet = TeacherDTOGet.builder()
                .id(teacher.getId())
                .scheduleId(teacher.getSchedule().getId())
                .name(teacher.getName())
                .dailyHours(teacher.getDailyHours())
                .weeklyHours(teacher.getWeeklyHours())
                .build();
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
    public Teacher fromPatchToEntity(TeacherDTOPatch teacherDTOPatch, Teacher teacherToUpdate) {

        if (teacherDTOPatch.getName() != null){
            if(teacherDTOPatch.getName().isBlank()) {
                throw new IllegalArgumentException("Teacher name cannot be blank");
            }
            teacherToUpdate.setName(teacherDTOPatch.getName());
        }

        if (teacherDTOPatch.getDailyHours() != null) {
            teacherToUpdate.setDailyHours(teacherDTOPatch.getDailyHours());
        }

        if (teacherDTOPatch.getWeeklyHours() != null) {
            teacherToUpdate.setWeeklyHours(teacherDTOPatch.getWeeklyHours());
        }

        return teacherToUpdate;

    }
}
