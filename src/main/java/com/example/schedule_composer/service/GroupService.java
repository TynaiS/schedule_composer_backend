package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.GroupDTOGet;
import com.example.schedule_composer.entity.Group;
import com.example.schedule_composer.repository.GroupRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<GroupDTOGet> getGroups() {
//        return groupRepository.findAll();
        return null;
//        to be implemented;
    }

    public GroupDTOGet getGroupById(Long id) {
//        return groupRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Group not found with id " + id));
        return null;
//        to be implemented;
    }
}
