package com.example.schedule_composer.mappers.impl;

import com.example.schedule_composer.dto.get.ScheduleSharedItemDTOGet;
import com.example.schedule_composer.dto.patch.ScheduleSharedItemDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleSharedItemDTOPost;
import com.example.schedule_composer.entity.ScheduleSharedItem;
import com.example.schedule_composer.mappers.ScheduleSharedItemMapper;
import com.example.schedule_composer.mappers.SetupSharedItemMapper;
import com.example.schedule_composer.mappers.TimeSlotMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ScheduleSharedItemMapperImpl implements ScheduleSharedItemMapper {

    private final SetupSharedItemMapper setupSharedItemMapper;
    private final RoomMapperImpl roomMapper;
    private final TimeSlotMapper timeSlotMapper;


    @Override
    public ScheduleSharedItemDTOGet fromEntityToGet(ScheduleSharedItem schedule) {
        ScheduleSharedItemDTOGet scheduleGet = ScheduleSharedItemDTOGet.builder()
                .id(schedule.getId())
                .scheduleVersionId(schedule.getScheduleVersion().getId())
                .setupSharedItem(setupSharedItemMapper.fromEntityToGet(schedule.getSetupSharedItem()))
                .room(roomMapper.fromEntityToGet(schedule.getRoom()))
                .day(schedule.getDay())
                .timeSlots(timeSlotMapper.fromEntityListToGetList(schedule.getTimeSlots()))
                .teachingMode(schedule.getTeachingMode())
                .build();

        return scheduleGet;
    }

    @Override
    public List<ScheduleSharedItemDTOGet> fromEntityListToGetList(List<ScheduleSharedItem> scheduleSharedItems) {
        return scheduleSharedItems.stream()
                .map(this::fromEntityToGet)
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleSharedItem fromPostToEntity(ScheduleSharedItemDTOPost scheduleSharedItemDTOPost) {



        ScheduleSharedItem schedule = ScheduleSharedItem.builder()
                .day(scheduleSharedItemDTOPost.getDay())
                .teachingMode(scheduleSharedItemDTOPost.getTeachingMode())
                .build();
        return schedule;
    }

    @Override
    public ScheduleSharedItem fromPatchToEntity(ScheduleSharedItemDTOPatch scheduleSharedItemDTOPatch, ScheduleSharedItem scheduleSharedItemToUpdate) {


//        if(scheduleSharedItemDTOPatch.getSetupSharedItemId() != null){
//            SetupSharedItem setupSharedItem = setupSharedItemService.getEntityById(scheduleSharedItemDTOPatch.getSetupSharedItemId());
//            scheduleSharedItemToUpdate.setSetupSharedItem(setupSharedItem);
//        }

//        if(scheduleSharedItemDTOPatch.getRoomId() != null){
//            Room room = roomService.getEntityById(scheduleSharedItemDTOPatch.getRoomId());
//            scheduleSharedItemToUpdate.setRoom(room);
//        }

        if(scheduleSharedItemDTOPatch.getDay() != null){
            scheduleSharedItemToUpdate.setDay(scheduleSharedItemDTOPatch.getDay());
        }

//        if(scheduleSharedItemDTOPatch.getTimeSlotIds() != null && scheduleSharedItemDTOPatch.getTimeSlotIds().size() > 0){
//            List<TimeSlot> timeSlots = timeSlotService.checkIfAllExistAndGetEntities(scheduleSharedItemDTOPatch.getTimeSlotIds());
//            scheduleSharedItemToUpdate.setTimeSlots(timeSlots);
//        }


        if(scheduleSharedItemDTOPatch.getTeachingMode() != null){
            scheduleSharedItemToUpdate.setTeachingMode(scheduleSharedItemDTOPatch.getTeachingMode());
        }

        return scheduleSharedItemToUpdate;

    }
}
