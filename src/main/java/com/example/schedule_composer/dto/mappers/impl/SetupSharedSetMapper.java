package com.example.schedule_composer.dto.mappers.impl;

import com.example.schedule_composer.dto.get.SetupSharedSetDTOGet;
import com.example.schedule_composer.dto.mappers.DTOMapper;
import com.example.schedule_composer.dto.patch.SetupSharedSetDTOPatch;
import com.example.schedule_composer.dto.post.SetupSharedSetDTOPost;
import com.example.schedule_composer.entity.SetupSharedSet;
import com.example.schedule_composer.repository.SetupSharedSetRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SetupSharedSetMapper implements DTOMapper<SetupSharedSetDTOGet, SetupSharedSetDTOPost, SetupSharedSetDTOPatch, SetupSharedSet, Long> {

    private final SetupSharedSetRepository setupSharedSetRepository;

    @Override
    public SetupSharedSetDTOGet fromEntityToGet(SetupSharedSet setupSharedSet) {
        SetupSharedSetDTOGet setupSharedSetGet = new SetupSharedSetDTOGet(setupSharedSet.getId(), setupSharedSet.getName(), setupSharedSet.getHoursAWeek());
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
    public SetupSharedSet fromPatchToEntity(SetupSharedSetDTOPatch setupSharedSetDTOPatch, Long setupSharedSetId) {

        SetupSharedSet existingSetupSharedSet = setupSharedSetRepository.findById(setupSharedSetId)
                .orElseThrow(() -> new EntityNotFoundException("SetupSharedSet not found with id: " + setupSharedSetId));

        if (setupSharedSetDTOPatch.getName() != null){
            if(setupSharedSetDTOPatch.getName().isBlank()){
                throw new IllegalArgumentException("SetupSharedSet name cannot be blank");
            }
            existingSetupSharedSet.setName(setupSharedSetDTOPatch.getName());
        }


        if(setupSharedSetDTOPatch.getHoursAWeek() != null){
            existingSetupSharedSet.setHoursAWeek(setupSharedSetDTOPatch.getHoursAWeek());
        }


        return existingSetupSharedSet;

    }
}
