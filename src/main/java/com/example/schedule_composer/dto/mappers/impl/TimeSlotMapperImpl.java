package com.example.schedule_composer.dto.mappers.impl;

import com.example.schedule_composer.dto.get.TimeSlotDTOGet;
import com.example.schedule_composer.dto.mappers.TimeSlotMapper;
import com.example.schedule_composer.dto.patch.TimeSlotDTOPatch;
import com.example.schedule_composer.dto.post.TimeSlotDTOPost;
import com.example.schedule_composer.entity.TimeSlot;
import com.example.schedule_composer.repository.TimeSlotRepository;
import com.example.schedule_composer.utils.TimeSlotOrdered;
import com.example.schedule_composer.utils.TimeSlotValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class TimeSlotMapperImpl implements TimeSlotMapper {

    private final TimeSlotRepository timeSlotRepository;
    private final TimeSlotValidator timeSlotValidator;

    @Override
    public TimeSlotDTOGet fromEntityToGet(TimeSlot timeSlot) {
        TimeSlotDTOGet timeSlotGet = new TimeSlotDTOGet(timeSlot.getId(), timeSlot.getStartTime(), timeSlot.getEndTime());
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
                .startTime(timeSlotDTOPost.getStartTime())
                .endTime(timeSlotDTOPost.getEndTime())
                .build();

        return timeSlot;
    }

    @Override
    public TimeSlot fromPatchToEntity(TimeSlotDTOPatch timeSlotDTOPatch, Long timeSlotId) {

        TimeSlot existingTimeSlot = timeSlotRepository.findById(timeSlotId)
                .orElseThrow(() -> new EntityNotFoundException("Time slot not found with id: " + timeSlotId));


        LocalTime newStartTime = timeSlotDTOPatch.getStartTime();
        LocalTime newEndTime = timeSlotDTOPatch.getEndTime();

        if (newStartTime != null && newEndTime != null) {
            if (newStartTime.isAfter(newEndTime) || newStartTime.equals(newEndTime)) {
                throw new IllegalArgumentException("Start time must be before end time");
            }
            existingTimeSlot.setStartTime(newStartTime);
            existingTimeSlot.setEndTime(newEndTime);
        }


        else if (newStartTime != null) {
            if (existingTimeSlot.getEndTime() != null && newStartTime.isAfter(existingTimeSlot.getEndTime())) {
                throw new IllegalArgumentException("Start time must be before end time");
            }
            existingTimeSlot.setStartTime(newStartTime);
        }
        else if (newEndTime != null) {
            if (existingTimeSlot.getStartTime() != null && newEndTime.isBefore(existingTimeSlot.getStartTime())) {
                throw new IllegalArgumentException("Start time must be before end time");
            }
            existingTimeSlot.setEndTime(newEndTime);
        }

        return existingTimeSlot;
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
                        .startTime(ordered.getStartTime())
                        .endTime(ordered.getEndTime())
                        .build())
                .collect(Collectors.toList());
    }

}
