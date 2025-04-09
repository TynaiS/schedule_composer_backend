package com.example.schedule_composer.service.impl;

import com.example.schedule_composer.dto.get.GroupCourseTeacherDTOGet;
import com.example.schedule_composer.dto.mappers.GroupCourseTeacherMapper;
import com.example.schedule_composer.dto.patch.GroupCourseTeacherDTOPatch;
import com.example.schedule_composer.dto.post.GroupCourseTeacherDTOPost;
import com.example.schedule_composer.entity.GroupCourseTeacher;
import com.example.schedule_composer.repository.GroupCourseTeacherRepository;
import com.example.schedule_composer.service.GroupCourseTeacherService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupCourseTeacherServiceImpl implements GroupCourseTeacherService {

    private final GroupCourseTeacherRepository groupCourseTeacherRepository;
    private final GroupCourseTeacherMapper groupCourseTeacherMapper;

    @Autowired
    public GroupCourseTeacherServiceImpl(GroupCourseTeacherRepository groupCourseTeacherRepository,GroupCourseTeacherMapper groupCourseTeacherMapper){
        this.groupCourseTeacherRepository = groupCourseTeacherRepository;
        this.groupCourseTeacherMapper = groupCourseTeacherMapper;
    }

    @Override
    public GroupCourseTeacherDTOGet getById(Long id) {
        return groupCourseTeacherMapper.fromEntityToGet(getEntityById(id));
    }

    @Override
    public GroupCourseTeacher getEntityById(Long id) {
        GroupCourseTeacher entity = groupCourseTeacherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Group-Course-Teacher not found with id: " + id));
        return entity;
    }

    @Override
    public Boolean checkIfExists(Long id) {
        if (!groupCourseTeacherRepository.existsById(id)) {
            throw new EntityNotFoundException("Group-Course-Teacher not found with id: " + id);
        }
        return true;
    }

    @Override
    public List<GroupCourseTeacherDTOGet> getAll() {
        List<GroupCourseTeacher> entities = groupCourseTeacherRepository.findAll();

        return entities.stream()
                .map(groupCourseTeacherMapper::fromEntityToGet)
                .collect(Collectors.toList());
    }

    @Override
    public GroupCourseTeacherDTOGet create(GroupCourseTeacherDTOPost createDto) {
        GroupCourseTeacher savedEntity = groupCourseTeacherRepository.save(groupCourseTeacherMapper.fromPostToEntity(createDto));
        return groupCourseTeacherMapper.fromEntityToGet(savedEntity);
    }

    @Override
    public GroupCourseTeacherDTOGet update(Long id, GroupCourseTeacherDTOPatch updateDto) {
        GroupCourseTeacher updatedEntity = groupCourseTeacherRepository.save(groupCourseTeacherMapper.fromPatchToEntity(updateDto, id));
        return groupCourseTeacherMapper.fromEntityToGet(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if(checkIfExists(id)) groupCourseTeacherRepository.deleteById(id);
    }
}
