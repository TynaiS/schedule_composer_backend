package com.example.schedule_composer.mappers.impl;

import com.example.schedule_composer.dto.get.SetupSharedSetDTOGet;
import com.example.schedule_composer.dto.patch.SetupSharedSetDTOPatch;
import com.example.schedule_composer.dto.post.SetupSharedSetDTOPost;
import com.example.schedule_composer.entity.SetupSharedSet;
import com.example.schedule_composer.mappers.SetupSharedSetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SetupSharedSetMapperImpl implements SetupSharedSetMapper {

    @Override
    public SetupSharedSetDTOGet fromEntityToGet(SetupSharedSet setupSharedSet) {
        SetupSharedSetDTOGet setupSharedSetGet = SetupSharedSetDTOGet.builder()
                .id(setupSharedSet.getId())
                .scheduleVersionId(setupSharedSet.getScheduleVersion().getId())
                .name(setupSharedSet.getName())
                .hoursAWeek(setupSharedSet.getHoursAWeek())
                .build();
        return setupSharedSetGet;
    }

    @Override
    public List<SetupSharedSetDTOGet> fromEntityListToGetList(List<SetupSharedSet> setupSharedSets) {
        return setupSharedSets.stream()
                .map(this::fromEntityToGet)
                .collect(Collectors.toList());
    }

    @Override
    public SetupSharedSet fromPostToEntity(SetupSharedSetDTOPost setupSharedSetDTOPost) {

        SetupSharedSet setupSharedSet = SetupSharedSet.builder()
                .name(setupSharedSetDTOPost.getName())
                .hoursAWeek(setupSharedSetDTOPost.getHoursAWeek())
                .build();
        return setupSharedSet;
    }

    @Override
    public SetupSharedSet fromPatchToEntity(SetupSharedSetDTOPatch setupSharedSetDTOPatch, SetupSharedSet setupSharedSetToUpdate) {

        if (setupSharedSetDTOPatch.getName() != null){
            if(setupSharedSetDTOPatch.getName().isBlank()){
                throw new IllegalArgumentException("SetupSharedSet name cannot be blank");
            }
            setupSharedSetToUpdate.setName(setupSharedSetDTOPatch.getName());
        }


        if(setupSharedSetDTOPatch.getHoursAWeek() != null){
            setupSharedSetToUpdate.setHoursAWeek(setupSharedSetDTOPatch.getHoursAWeek());
        }


        return setupSharedSetToUpdate;

    }
}
