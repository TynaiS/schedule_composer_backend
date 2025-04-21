package com.example.schedule_composer.dto.mappers.impl;

import com.example.schedule_composer.dto.get.SetupSharedNameDTOGet;
import com.example.schedule_composer.dto.mappers.DTOMapper;
import com.example.schedule_composer.dto.patch.SetupSharedNameDTOPatch;
import com.example.schedule_composer.dto.post.SetupSharedNameDTOPost;
import com.example.schedule_composer.entity.SetupSharedName;
import com.example.schedule_composer.repository.SetupSharedNameRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SetupSharedNameMapper implements DTOMapper<SetupSharedNameDTOGet, SetupSharedNameDTOPost, SetupSharedNameDTOPatch, SetupSharedName, Long> {

    private final SetupSharedNameRepository setupSharedNameRepository;

    @Autowired
    public SetupSharedNameMapper(SetupSharedNameRepository setupSharedNameRepository) {
        this.setupSharedNameRepository = setupSharedNameRepository;
    }
    @Override
    public SetupSharedNameDTOGet fromEntityToGet(SetupSharedName setupSharedName) {
        SetupSharedNameDTOGet setupSharedNameGet = new SetupSharedNameDTOGet(setupSharedName.getId(), setupSharedName.getName());
        return setupSharedNameGet;
    }

    @Override
    public List<SetupSharedNameDTOGet> fromEntityListToGetList(List<SetupSharedName> setupSharedNames) {
        return setupSharedNames.stream()
                .map(this::fromEntityToGet)
                .collect(Collectors.toList());
    }

    @Override
    public SetupSharedName fromPostToEntity(SetupSharedNameDTOPost setupSharedNameDTOPost) {

        SetupSharedName setupSharedName = SetupSharedName.builder()
                .name(setupSharedNameDTOPost.getName())
                .build();
        return setupSharedName;
    }

    @Override
    public SetupSharedName fromPatchToEntity(SetupSharedNameDTOPatch setupSharedNameDTOPatch, Long setupSharedNameId) {

        SetupSharedName existingSetupSharedName = setupSharedNameRepository.findById(setupSharedNameId)
                .orElseThrow(() -> new EntityNotFoundException("SetupSharedName not found with id: " + setupSharedNameId));

        if (setupSharedNameDTOPatch.getName() != null){
            if(setupSharedNameDTOPatch.getName().isBlank()){
                throw new IllegalArgumentException("SetupSharedName name cannot be blank");
            }
            existingSetupSharedName.setName(setupSharedNameDTOPatch.getName());
        }

        return existingSetupSharedName;

    }
}
