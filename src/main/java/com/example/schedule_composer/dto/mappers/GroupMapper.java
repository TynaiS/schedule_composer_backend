package com.example.schedule_composer.dto.mappers;

import com.example.schedule_composer.dto.get.GroupDTOGet;
import com.example.schedule_composer.dto.patch.GroupDTOPatch;
import com.example.schedule_composer.dto.post.GroupDTOPost;
import com.example.schedule_composer.entity.Group;
import com.example.schedule_composer.repository.GroupRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GroupMapper implements DTOMapper<GroupDTOGet, GroupDTOPost, GroupDTOPatch, Group, Long>{

    private final GroupRepository groupRepository;

    @Autowired
    public GroupMapper(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }
    @Override
    public GroupDTOGet fromEntityToGet(Group group) {
        GroupDTOGet groupGet = new GroupDTOGet(group.getId(), group.getName());
        return groupGet;
    }

    @Override
    public Group fromPostToEntity(GroupDTOPost groupDTOPost) {
        Group group = Group.builder()
                .name(groupDTOPost.getName())
                .build();
        return group;
    }

    @Override
    public Group fromPatchToEntity(GroupDTOPatch groupDTOPatch, Long groupId) {

        Group existingGroup = groupRepository.findById(groupId)
                .orElseThrow(() -> new EntityNotFoundException("Group not found with id: " + groupId));

        if (groupDTOPatch.getName() != null){
            if(groupDTOPatch.getName().isBlank()){
                throw new IllegalArgumentException("Group name cannot be blank");
            }
            existingGroup.setName(groupDTOPatch.getName());
        }
        return existingGroup;

    }
}
