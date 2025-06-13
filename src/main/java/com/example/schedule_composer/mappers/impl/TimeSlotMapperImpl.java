package com.example.schedule_composer.mappers.impl;

import com.example.schedule_composer.dto.get.TimeSlotDTOGet;
import com.example.schedule_composer.dto.patch.TimeSlotDTOPatch;
import com.example.schedule_composer.dto.post.TimeSlotDTOPost;
import com.example.schedule_composer.entity.TimeSlot;
import com.example.schedule_composer.mappers.TimeSlotMapper;
import com.example.schedule_composer.utils.TimeSlotOrdered;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class TimeSlotMapperImpl implements TimeSlotMapper {

    @Override
    public TimeSlotDTOGet fromEntityToGet(TimeSlot timeSlot) {
        TimeSlotDTOGet timeSlotGet = TimeSlotDTOGet.builder()
                .id(timeSlot.getId())
                .scheduleId(timeSlot.getSchedule().getId())
                .isLunchAllowed(timeSlot.getIsLunchAllowed())
                .startTime(timeSlot.getStartTime())
                .endTime(timeSlot.getEndTime())
                .build();
        return timeSlotGet;
    }

    @Override
    public List<TimeSlotDTOGet> fromEntityListToGetList(List<TimeSlot> timeSlots) {
        return timeSlots.stream()
                .map(this::fromEntityToGet)
                .collect(Collectors.toList());
    }

    @Override
    public TimeSlot fromPostToEntity(TimeSlotDTOPost timeSlotDTOPost) {

        LocalTime startTime = timeSlotDTOPost.getStartTime();
        LocalTime endTime = timeSlotDTOPost.getEndTime();

        if (startTime.isAfter(endTime) || startTime.equals(endTime)) {
            throw new IllegalArgumentException("Start time must be before end time");
        }

        TimeSlot timeSlot = TimeSlot.builder()
                .isLunchAllowed(timeSlotDTOPost.getIsLunchAllowed())
                .startTime(timeSlotDTOPost.getStartTime())
                .endTime(timeSlotDTOPost.getEndTime())
                .build();

        return timeSlot;
    }

    @Override
    public TimeSlot fromPatchToEntity(TimeSlotDTOPatch timeSlotDTOPatch, TimeSlot timeSlotToUpdate) {

        LocalTime newStartTime = timeSlotDTOPatch.getStartTime();
        LocalTime newEndTime = timeSlotDTOPatch.getEndTime();

        if (newStartTime != null && newEndTime != null) {
            if (newStartTime.isAfter(newEndTime) || newStartTime.equals(newEndTime)) {
                throw new IllegalArgumentException("Start time must be before end time");
            }
            timeSlotToUpdate.setStartTime(newStartTime);
            timeSlotToUpdate.setEndTime(newEndTime);
        }


        else if (newStartTime != null) {
            if (timeSlotToUpdate.getEndTime() != null && newStartTime.isAfter(timeSlotToUpdate.getEndTime())) {
                throw new IllegalArgumentException("Start time must be before end time");
            }
            timeSlotToUpdate.setStartTime(newStartTime);
        }
        else if (newEndTime != null) {
            if (timeSlotToUpdate.getStartTime() != null && newEndTime.isBefore(timeSlotToUpdate.getStartTime())) {
                throw new IllegalArgumentException("Start time must be before end time");
            }
            timeSlotToUpdate.setEndTime(newEndTime);
        }


        if (timeSlotDTOPatch.getIsLunchAllowed() != null) {
            timeSlotToUpdate.setIsLunchAllowed(timeSlotDTOPatch.getIsLunchAllowed());
        }

        return timeSlotToUpdate;
    }


    @Override
    public List<TimeSlotOrdered> fromEntityListToOrderedList(List<TimeSlot> timeSlots) {
        List<TimeSlot> sorted = timeSlots.stream()
                .sorted(Comparator.comparing(TimeSlot::getStartTime))
                .collect(Collectors.toList());

        return IntStream.range(0, sorted.size())
                .mapToObj(i -> {
                    TimeSlot ts = sorted.get(i);
                    return TimeSlotOrdered.builder()
                            .id(ts.getId())
                            .seqNumber(i + 1)
                            .schedule(ts.getSchedule())
                            .isLunchAllowed(ts.getIsLunchAllowed())
                            .startTime(ts.getStartTime())
                            .endTime(ts.getEndTime())
                            .build();
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<TimeSlot> fromOrderedListToEntityList(List<TimeSlotOrdered> orderedSlots) {
        return orderedSlots.stream()
                .map(ordered -> TimeSlot.builder()
                        .id(ordered.getId())
                        .schedule(ordered.getSchedule())
                        .isLunchAllowed(ordered.getIsLunchAllowed())
                        .startTime(ordered.getStartTime())
                        .endTime(ordered.getEndTime())
                        .build())
                .collect(Collectors.toList());
    }

}
