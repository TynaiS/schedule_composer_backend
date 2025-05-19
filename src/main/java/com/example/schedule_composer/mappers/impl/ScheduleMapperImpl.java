package com.example.schedule_composer.mappers.impl;

import com.example.schedule_composer.dto.get.ScheduleDTOGet;
import com.example.schedule_composer.dto.patch.ScheduleDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleDTOPost;
import com.example.schedule_composer.entity.Schedule;
import com.example.schedule_composer.entity.User;
import com.example.schedule_composer.mappers.ScheduleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ScheduleMapperImpl implements ScheduleMapper {

    @Override
    public ScheduleDTOGet fromEntityToGet(Schedule schedule) {
        return ScheduleDTOGet.builder()
                .id(schedule.getId())
                .name(schedule.getName())
                .editorsEmails(schedule.getEditors().stream()
                        .map(User::getEmail)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public List<ScheduleDTOGet> fromEntityListToGetList(List<Schedule> schedules) {
        return schedules.stream()
                .map(this::fromEntityToGet)
                .collect(Collectors.toList());
    }

    @Override
    public Schedule fromPostToEntity(ScheduleDTOPost scheduleDTOPost) {

        Schedule schedule = Schedule.builder()
                .name(scheduleDTOPost.getName())
                .build();
        return schedule;
    }

    @Override
    public Schedule fromPatchToEntity(ScheduleDTOPatch scheduleDTOPatch, Schedule scheduleToUpdate) {

        if (scheduleDTOPatch.getName() != null) {
            if (scheduleDTOPatch.getName().isBlank()) {
                throw new IllegalArgumentException("Schedule name cannot be blank");
            }
            scheduleToUpdate.setName(scheduleDTOPatch.getName());
        }

        return scheduleToUpdate;

    }
}
