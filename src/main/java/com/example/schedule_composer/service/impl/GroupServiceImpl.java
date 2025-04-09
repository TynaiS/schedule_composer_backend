package com.example.schedule_composer.service.impl;

import com.example.schedule_composer.dto.get.GroupDTOGet;
import com.example.schedule_composer.dto.mappers.GroupMapper;
import com.example.schedule_composer.dto.patch.GroupDTOPatch;
import com.example.schedule_composer.dto.post.GroupDTOPost;
import com.example.schedule_composer.entity.Group;
import com.example.schedule_composer.repository.GroupRepository;
import com.example.schedule_composer.service.GroupService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository,GroupMapper groupMapper){
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
    }

    @Override
    public GroupDTOGet getById(Long id) {
        return groupMapper.fromEntityToGet(getEntityById(id));
    }

    @Override
    public Group getEntityById(Long id) {
        Group entity = groupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Group not found with id: " + id));
        return entity;
    }

    @Override
    public Boolean checkIfExists(Long id) {
        if (!groupRepository.existsById(id)) {
            throw new EntityNotFoundException("Group not found with id: " + id);
        }
        return true;
    }

    @Override
    public List<GroupDTOGet> getAll() {
        List<Group> entities = groupRepository.findAll();

        return entities.stream()
                .map(groupMapper::fromEntityToGet)
                .collect(Collectors.toList());
    }

    @Override
    public GroupDTOGet create(GroupDTOPost createDto) {
        Group savedEntity = groupRepository.save(groupMapper.fromPostToEntity(createDto));
        return groupMapper.fromEntityToGet(savedEntity);
    }

    @Override
    public GroupDTOGet update(Long id, GroupDTOPatch updateDto) {
        Group updatedEntity = groupRepository.save(groupMapper.fromPatchToEntity(updateDto, id));
        return groupMapper.fromEntityToGet(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if(checkIfExists(id)) groupRepository.deleteById(id);
    }
}
