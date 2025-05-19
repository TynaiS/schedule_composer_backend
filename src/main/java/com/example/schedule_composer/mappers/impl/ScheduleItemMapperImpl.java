package com.example.schedule_composer.mappers.impl;

import com.example.schedule_composer.dto.get.ScheduleItemDTOGet;
import com.example.schedule_composer.dto.patch.ScheduleItemDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleItemDTOPost;
import com.example.schedule_composer.entity.ScheduleItem;
import com.example.schedule_composer.mappers.ScheduleItemMapper;
import com.example.schedule_composer.mappers.SetupItemMapper;
import com.example.schedule_composer.mappers.TimeSlotMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ScheduleItemMapperImpl implements ScheduleItemMapper {

    private final SetupItemMapper setupItemMapper;
    private final RoomMapperImpl roomMapper;
    private final TimeSlotMapper timeSlotMapper;


    @Override
    public ScheduleItemDTOGet fromEntityToGet(ScheduleItem scheduleItem) {
        ScheduleItemDTOGet scheduleGet = ScheduleItemDTOGet.builder()
                .id(scheduleItem.getId())
                .scheduleVersionId(scheduleItem.getScheduleVersion().getId())
                .setupItem(setupItemMapper.fromEntityToGet(scheduleItem.getSetupItem()))
                .room(roomMapper.fromEntityToGet(scheduleItem.getRoom()))
                .day(scheduleItem.getDay())
                .timeSlots(timeSlotMapper.fromEntityListToGetList(scheduleItem.getTimeSlots()))
                .teachingMode(scheduleItem.getTeachingMode())
                .build();

        return scheduleGet;
    }

    @Override
    public List<ScheduleItemDTOGet> fromEntityListToGetList(List<ScheduleItem> scheduleItems) {
        return scheduleItems.stream()
                .map(this::fromEntityToGet)
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleItem fromPostToEntity(ScheduleItemDTOPost scheduleItemDTOPost) {



        ScheduleItem scheduleItem = ScheduleItem.builder()
                .day(scheduleItemDTOPost.getDay())
                .teachingMode(scheduleItemDTOPost.getTeachingMode())
                .build();
        return scheduleItem;
    }

    @Override
    public ScheduleItem fromPatchToEntity(ScheduleItemDTOPatch scheduleItemDTOPatch, ScheduleItem scheduleItemToUpdate) {

        if(scheduleItemDTOPatch.getDay() != null){
            scheduleItemToUpdate.setDay(scheduleItemDTOPatch.getDay());
        }

        if(scheduleItemDTOPatch.getTeachingMode() != null){
            scheduleItemToUpdate.setTeachingMode(scheduleItemDTOPatch.getTeachingMode());
        }

        return scheduleItemToUpdate;

    }
}
