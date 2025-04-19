package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.SetupDTOGet;
import com.example.schedule_composer.dto.patch.SetupDTOPatch;
import com.example.schedule_composer.dto.post.SetupDTOPost;
import com.example.schedule_composer.entity.Setup;


import java.util.List;

public interface SetupService {

    SetupDTOGet getById(Long id);

    Setup getEntityById(Long id);

    Boolean checkIfExists(Long id);

    List<SetupDTOGet> getAll();

    List<Setup> getAllEntities();

    SetupDTOGet create(SetupDTOPost createDto);

    SetupDTOGet update(Long id, SetupDTOPatch updateDto);

    void deleteById(Long id);
}
