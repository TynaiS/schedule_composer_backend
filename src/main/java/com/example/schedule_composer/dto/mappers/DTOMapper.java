package com.example.schedule_composer.dto.mappers;

public interface DTOMapper<GET_DTO, POST_DTO, PATCH_DTO, ENTITY, ID>{

    GET_DTO fromEntityToGet(ENTITY entity);
    ENTITY fromPostToEntity(POST_DTO postDto);
    ENTITY fromPatchToEntity(PATCH_DTO patchDto, ID id);
}
