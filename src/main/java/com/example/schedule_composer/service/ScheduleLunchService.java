package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.ScheduleLunchDTOGet;
import com.example.schedule_composer.dto.patch.ScheduleLunchDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleLunchDTOPost;
import com.example.schedule_composer.entity.ScheduleLunch;

import java.util.List;

public interface ScheduleLunchService {

    ScheduleLunchDTOGet getById(Long id);

    ScheduleLunch getEntityById(Long id);

    Boolean checkIfExists(Long id);

    List<ScheduleLunchDTOGet> getAll();

    ScheduleLunchDTOGet create(ScheduleLunchDTOPost createDto);

    ScheduleLunchDTOGet update(Long id, ScheduleLunchDTOPatch updateDto);

    void deleteById(Long id);
}
