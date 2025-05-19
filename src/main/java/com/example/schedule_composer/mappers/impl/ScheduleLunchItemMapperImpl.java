package com.example.schedule_composer.mappers.impl;

import com.example.schedule_composer.dto.get.ScheduleLunchItemDTOGet;
import com.example.schedule_composer.dto.patch.ScheduleLunchItemDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleLunchItemDTOPost;
import com.example.schedule_composer.entity.ScheduleLunchItem;
import com.example.schedule_composer.mappers.GroupMapper;
import com.example.schedule_composer.mappers.ScheduleLunchItemMapper;
import com.example.schedule_composer.mappers.TimeSlotMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ScheduleLunchItemMapperImpl implements ScheduleLunchItemMapper {

    private final GroupMapper groupMapper;
    private final TimeSlotMapper timeSlotMapper;

    @Override
    public ScheduleLunchItemDTOGet fromEntityToGet(ScheduleLunchItem scheduleLunchItem) {
        ScheduleLunchItemDTOGet scheduleLunchGet = ScheduleLunchItemDTOGet.builder()
                .id(scheduleLunchItem.getId())
                .scheduleVersionId(scheduleLunchItem.getScheduleVersion().getId())
                .group(groupMapper.fromEntityToGet(scheduleLunchItem.getGroup()))
                .day(scheduleLunchItem.getDay())
                .timeSlots(timeSlotMapper.fromEntityListToGetList(scheduleLunchItem.getTimeSlots()))
                .build();
        return scheduleLunchGet;
    }

    @Override
    public List<ScheduleLunchItemDTOGet> fromEntityListToGetList(List<ScheduleLunchItem> scheduleLunchItems) {
        return scheduleLunchItems.stream()
                .map(this::fromEntityToGet)
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleLunchItem fromPostToEntity(ScheduleLunchItemDTOPost scheduleLunchItemDTOPost) {

        ScheduleLunchItem scheduleLunchItem = ScheduleLunchItem.builder()
                .day(scheduleLunchItemDTOPost.getDay())
                .build();
        return scheduleLunchItem;
    }

    @Override
    public ScheduleLunchItem fromPatchToEntity(ScheduleLunchItemDTOPatch scheduleLunchItemDTOPatch, ScheduleLunchItem scheduleLunchItemToUpdate) {


//        if(scheduleLunchItemDTOPatch.getGroupId() != null){
//            Group group = groupService.getEntityById(scheduleLunchItemDTOPatch.getGroupId());
//            scheduleLunchItemToUpdate.setGroup(group);
//        }

        if(scheduleLunchItemDTOPatch.getDay() != null){
            scheduleLunchItemToUpdate.setDay(scheduleLunchItemDTOPatch.getDay());
        }

//        if(scheduleLunchItemDTOPatch.getTimeSlotIds() != null && scheduleLunchItemDTOPatch.getTimeSlotIds().size() > 0){
//            List<TimeSlot> timeSlots = timeSlotService.checkIfAllExistAndGetEntities(scheduleLunchItemDTOPatch.getTimeSlotIds());
//            scheduleLunchItemToUpdate.setTimeSlots(timeSlots);
//        }

        return scheduleLunchItemToUpdate;

    }
}
