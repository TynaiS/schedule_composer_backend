package com.example.schedule_composer.mappers.impl;

import com.example.schedule_composer.dto.get.SetupItemDTOGet;
import com.example.schedule_composer.dto.patch.SetupItemDTOPatch;
import com.example.schedule_composer.dto.post.SetupItemDTOPost;
import com.example.schedule_composer.entity.SetupItem;
import com.example.schedule_composer.mappers.CourseMapper;
import com.example.schedule_composer.mappers.GroupMapper;
import com.example.schedule_composer.mappers.SetupItemMapper;
import com.example.schedule_composer.mappers.TeacherMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SetupItemMapperImpl implements SetupItemMapper {

    private final GroupMapper groupMapper;
    private final CourseMapper courseMapper;
    private final TeacherMapper teacherMapper;

    @Override
    public SetupItemDTOGet fromEntityToGet(SetupItem setupItem) {
        SetupItemDTOGet setupGet = SetupItemDTOGet.builder()
                .id(setupItem.getId())
                .scheduleVersionId(setupItem.getScheduleVersion().getId())
                .group(groupMapper.fromEntityToGet(setupItem.getGroup()))
                .course(courseMapper.fromEntityToGet(setupItem.getCourse()))
                .teacher(teacherMapper.fromEntityToGet(setupItem.getTeacher()))
                .coursePriority(setupItem.getCoursePriority())
                .hoursAWeek(setupItem.getHoursAWeek())
//  .hoursTotal(setupItem.getHoursTotal())
//  .weeksTotal(setupItem.getWeeksTotal())
                .hoursInLab(setupItem.getHoursInLab())
                .preferredRoomType(setupItem.getPreferredRoomType())
                .build();

        return setupGet;
    }

    @Override
    public List<SetupItemDTOGet> fromEntityListToGetList(List<SetupItem> setupItems) {
        return setupItems.stream()
                .map(this::fromEntityToGet)
                .collect(Collectors.toList());
    }

    @Override
    public SetupItem fromPostToEntity(SetupItemDTOPost setupDTOPost) {

        SetupItem setupItem = SetupItem.builder()
                .coursePriority(setupDTOPost.getCoursePriority())
                .hoursAWeek(setupDTOPost.getHoursAWeek())
//                .hoursTotal(setupDTOPost.getHoursTotal())
//                .weeksTotal(setupDTOPost.getWeeksTotal())
                .hoursInLab(setupDTOPost.getHoursInLab())
                .preferredRoomType(setupDTOPost.getPreferredRoomType())
                .build();
        return setupItem;
    }

    @Override
    public SetupItem fromPatchToEntity(SetupItemDTOPatch setupDTOPatch, SetupItem setupItemToUpdate) {

        if(setupDTOPatch.getCoursePriority() != null){
            setupItemToUpdate.setCoursePriority(setupDTOPatch.getCoursePriority());
        }

        if(setupDTOPatch.getHoursAWeek() != null){
            setupItemToUpdate.setHoursAWeek(setupDTOPatch.getHoursAWeek());
        }

//        if(setupDTOPatch.getHoursTotal() != null){
//            setupItemToUpdate.setHoursTotal(setupDTOPatch.getHoursTotal());
//        }
//
//        if(setupDTOPatch.getWeeksTotal() != null){
//            setupItemToUpdate.setWeeksTotal(setupDTOPatch.getWeeksTotal());
//        }

        if(setupDTOPatch.getHoursInLab() != null){
            setupItemToUpdate.setHoursInLab(setupDTOPatch.getHoursInLab());
        }

        if(setupDTOPatch.getPreferredRoomType() != null){
            setupItemToUpdate.setPreferredRoomType(setupDTOPatch.getPreferredRoomType());
        }

        return setupItemToUpdate;

    }
}
