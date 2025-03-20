package com.example.schedule_composer.utils;

import com.example.schedule_composer.entity.TimeSlot;
import com.example.schedule_composer.repository.TimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.util.List;

@Component
public class TimeSlotValidator {

    private final TimeSlotRepository timeSlotRepository;

    @Autowired
    public TimeSlotValidator(TimeSlotRepository timeSlotRepository) {
        this.timeSlotRepository = timeSlotRepository;
    }

//    public TimeSlot validateSequenceOrder(TimeSlot newTimeSlot){
//        Boolean isValid = true;
//        if(newTimeSlot.getId() != null) {
//            List<TimeSlot> timeSlots = timeSlotRepository.findAll()
//                    .stream()
//                    .filter(timeSlot -> !timeSlot.getId().equals(newTimeSlot.getId()))
//                    .toList();
//            isValid = validateSequenceOrder2(newTimeSlot, timeSlots);
//        } else {
//            isValid = validateSequenceOrder2(newTimeSlot,timeSlotRepository.findAll());
//        }
//        if(!isValid) {
//            throw new IllegalArgumentException("Invalid sequence-order of time-slot or intersection of time-slots");
//        }
//        return newTimeSlot;
//    }

//    public Boolean validateSequenceOrder2(TimeSlot newTimeSlot, List<TimeSlot> timeSlots) {
//        Boolean isValid = true;
//        for (TimeSlot timeSlot : timeSlots){
//            if((timeSlot.getSequenceNumber() < newTimeSlot.getSequenceNumber() && newTimeSlot.getStartTime().isBefore(timeSlot.getEndTime())) ||
//                    (timeSlot.getSequenceNumber() > newTimeSlot.getSequenceNumber() && newTimeSlot.getEndTime().isAfter(timeSlot.getStartTime())) ||
//                    (timeSlot.getSequenceNumber().equals(newTimeSlot.getSequenceNumber()))) {
//                isValid = false;
//                System.out.println(timeSlot.getSequenceNumber() + " " + newTimeSlot.getSequenceNumber());
//                break;
//            }
//        }
//        return isValid;
//    }

}
