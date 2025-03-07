package com.example.schedule_composer.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CrudService<GET_DTO, POST_DTO, PATCH_DTO, ENTITY, ID> {
    GET_DTO getById(ID id);
    ENTITY getEntityById(ID id);
    Boolean checkIfExists(ID id);
    List<GET_DTO> getAll();
    GET_DTO create(POST_DTO createDto);
    GET_DTO update(ID id, PATCH_DTO updateDto);
    void deleteById(ID id);
}

