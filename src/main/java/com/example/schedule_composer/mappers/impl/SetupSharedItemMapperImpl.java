package com.example.schedule_composer.mappers.impl;

import com.example.schedule_composer.dto.get.SetupSharedItemDTOGet;
import com.example.schedule_composer.dto.patch.SetupSharedItemDTOPatch;
import com.example.schedule_composer.dto.post.SetupSharedItemDTOPost;
import com.example.schedule_composer.entity.SetupSharedItem;
import com.example.schedule_composer.mappers.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SetupSharedItemMapperImpl implements SetupSharedItemMapper {

    private final CourseMapper courseMapper;
    private final TeacherMapper teacherMapper;
    private final GroupMapper groupMapper;
    private final SetupSharedSetMapper setupSharedSetMapper;


    @Override
    public SetupSharedItemDTOGet fromEntityToGet(SetupSharedItem setupSharedItem) {
        SetupSharedItemDTOGet setupSharedGet = SetupSharedItemDTOGet.builder()
                .id(setupSharedItem.getId())
                .setupSharedSet(setupSharedSetMapper.fromEntityToGet(setupSharedItem.getSetupSharedSet()))
                .groups(groupMapper.fromEntityListToGetList(setupSharedItem.getGroups()))
                .course(courseMapper.fromEntityToGet(setupSharedItem.getCourse()))
                .teacher(teacherMapper.fromEntityToGet(setupSharedItem.getTeacher()))
                .coursePriority(setupSharedItem.getCoursePriority())
                .hoursInLab(setupSharedItem.getHoursInLab())
                .preferredRoomType(setupSharedItem.getPreferredRoomType())
                .build();

        return setupSharedGet;
    }

    @Override
    public List<SetupSharedItemDTOGet> fromEntityListToGetList(List<SetupSharedItem> setupSharedItems) {
        return setupSharedItems.stream()
                .map(this::fromEntityToGet)
                .collect(Collectors.toList());
    }

    @Override
    public SetupSharedItem fromPostToEntity(SetupSharedItemDTOPost setupSharedDTOPost) {

        SetupSharedItem setupSharedItem = SetupSharedItem.builder()
                .coursePriority(setupSharedDTOPost.getCoursePriority())
                .hoursInLab(setupSharedDTOPost.getHoursInLab())
                .preferredRoomType(setupSharedDTOPost.getPreferredRoomType())
                .build();
        return setupSharedItem;
    }

    @Override
    public SetupSharedItem fromPatchToEntity(SetupSharedItemDTOPatch setupSharedDTOPatch, SetupSharedItem setupSharedItemToUpdate) {

        if(setupSharedDTOPatch.getCoursePriority() != null){
            setupSharedItemToUpdate.setCoursePriority(setupSharedDTOPatch.getCoursePriority());
        }

        if(setupSharedDTOPatch.getHoursInLab() != null){
            setupSharedItemToUpdate.setHoursInLab(setupSharedDTOPatch.getHoursInLab());
        }

        if(setupSharedDTOPatch.getPreferredRoomType() != null){
            setupSharedItemToUpdate.setPreferredRoomType(setupSharedDTOPatch.getPreferredRoomType());
        }

        return setupSharedItemToUpdate;

    }
}
