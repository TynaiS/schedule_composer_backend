package com.example.schedule_composer.dto.mappers;

import com.example.schedule_composer.dto.get.TimeSlotDTOGet;
import com.example.schedule_composer.dto.patch.TimeSlotDTOPatch;
import com.example.schedule_composer.dto.post.TimeSlotDTOPost;
import com.example.schedule_composer.entity.TimeSlot;
import com.example.schedule_composer.repository.TimeSlotRepository;
import com.example.schedule_composer.utils.TimeSlotValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class TimeSlotMapper implements DTOMapper<TimeSlotDTOGet, TimeSlotDTOPost, TimeSlotDTOPatch, TimeSlot, Long>{

    private final TimeSlotRepository timeSlotRepository;
    private final TimeSlotValidator timeSlotValidator;

    @Autowired
    public TimeSlotMapper(TimeSlotRepository timeSlotRepository,  TimeSlotValidator timeSlotValidator) {
        this.timeSlotRepository = timeSlotRepository;
        this.timeSlotValidator = timeSlotValidator;
    }

    @Override
    public TimeSlotDTOGet fromEntityToGet(TimeSlot timeSlot) {
        TimeSlotDTOGet timeSlotGet = new TimeSlotDTOGet(timeSlot.getId(), timeSlot.getStartTime(), timeSlot.getEndTime());
        return timeSlotGet;
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
}
