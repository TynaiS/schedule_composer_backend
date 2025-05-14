package com.example.schedule_composer.mappers;

import java.util.List;

public interface DTOMapper<GET_DTO, POST_DTO, PATCH_DTO, ENTITY, ID>{

    GET_DTO fromEntityToGet(ENTITY entity);
    List<GET_DTO> fromEntityListToGetList(List<ENTITY> entities);
    ENTITY fromPostToEntity(POST_DTO postDto);
    ENTITY fromPatchToEntity(PATCH_DTO patchDto, ID id);
}
