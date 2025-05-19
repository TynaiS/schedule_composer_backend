package com.example.schedule_composer.mappers.impl;

import com.example.schedule_composer.dto.get.ScheduleVersionDTOGet;
import com.example.schedule_composer.dto.patch.ScheduleVersionDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleVersionDTOPost;
import com.example.schedule_composer.entity.ScheduleVersion;
import com.example.schedule_composer.mappers.ScheduleVersionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ScheduleVersionMapperImpl implements ScheduleVersionMapper {

    @Override
    public ScheduleVersionDTOGet fromEntityToGet(ScheduleVersion scheduleVersion) {
        ScheduleVersionDTOGet scheduleVersionGet  = ScheduleVersionDTOGet.builder()
                .id(scheduleVersion.getId())
                .scheduleId(scheduleVersion.getSchedule().getId())
                .name(scheduleVersion.getName())
                .build();
        return scheduleVersionGet;
    }

    @Override
    public List<ScheduleVersionDTOGet> fromEntityListToGetList(List<ScheduleVersion> scheduleVersions) {
        return scheduleVersions.stream()
                .map(this::fromEntityToGet)
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleVersion fromPostToEntity(ScheduleVersionDTOPost scheduleVersionDTOPost) {
        ScheduleVersion scheduleVersion = ScheduleVersion.builder()
                .name(scheduleVersionDTOPost.getName())
                .build();
        return scheduleVersion;
    }

    @Override
    public ScheduleVersion fromPatchToEntity(ScheduleVersionDTOPatch scheduleVersionDTOPatch, ScheduleVersion scheduleVersionToUpdate) {

        if (scheduleVersionDTOPatch.getName() != null) {
            if (scheduleVersionDTOPatch.getName().isBlank()) {
                throw new IllegalArgumentException("Schedule name cannot be blank");
            }
            scheduleVersionToUpdate.setName(scheduleVersionDTOPatch.getName());
        }

        return scheduleVersionToUpdate;

    }
}
