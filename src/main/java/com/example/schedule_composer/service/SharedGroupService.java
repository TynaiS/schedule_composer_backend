package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.SharedGroupDTOGet;
import com.example.schedule_composer.dto.patch.SharedGroupDTOPatch;
import com.example.schedule_composer.dto.post.SharedGroupDTOPost;
import com.example.schedule_composer.entity.SharedGroup;



import java.util.List;

public interface SharedGroupService {

    SharedGroupDTOGet getById(Long id);

    SharedGroup getEntityById(Long id);

    Boolean checkIfExists(Long id);

    List<SharedGroupDTOGet> getAll();

    SharedGroupDTOGet create(SharedGroupDTOPost createDto);

    SharedGroupDTOGet update(Long id, SharedGroupDTOPatch updateDto);

    void deleteById(Long id);
}
