package com.example.schedule_composer.schedule_generator;

import com.example.schedule_composer.mappers.TimeSlotMapper;
import com.example.schedule_composer.entity.TimeSlot;
import com.example.schedule_composer.repository.TimeSlotRepository;
import com.example.schedule_composer.service.TimeSlotService;
import com.example.schedule_composer.utils.TimeSlotOrdered;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class TimeFrameManager {

    private final TimeSlotRepository timeSlotRepository;
    private final TimeSlotService timeSlotService;
    private final TimeSlotMapper timeSlotMapper;
    private static final Random random = new Random();


    public List<TimeSlot> getRandomTimeSlotsSet(Long userId, Long scheduleId, int academicHours) {
        List<TimeSlot> timeSlots = timeSlotService.getAllEntitiesForUserSchedule(userId, scheduleId);
        List<TimeSlotOrdered> timeSlotsOrdered = timeSlotMapper.fromEntityListToOrderedList(timeSlots);


        List<TimeSlotOrdered> timeFrame =
                switch (academicHours) {
                    case 1:
                        yield getRandomSlice(timeSlotsOrdered, 1);
                    case 2:
                        yield getRandomSlice(timeSlotsOrdered,2);
                    case 3:
                        yield getRandomSlice(timeSlotsOrdered, 3);
                    case 4:
                        yield getRandomSlice(timeSlotsOrdered, 4);
                    default:
                        yield getRandomSlice(timeSlotsOrdered,1);
                };

        return timeSlotMapper.fromOrderedListToEntityList(timeFrame);
    }



    private static List<TimeSlotOrdered> getRandomSlice(List<TimeSlotOrdered> list, int size) {
        List<List<TimeSlotOrdered>> validSlices = new ArrayList<>();

        for (int i = 0; i <= list.size() - size; i++) {
            boolean isConsecutive = true;
            for (int j = 0; j < size - 1; j++) {
                int currentSeq = list.get(i + j).getSeqNumber();
                int nextSeq = list.get(i + j + 1).getSeqNumber();
                if (nextSeq != currentSeq + 1) {
                    isConsecutive = false;
                    break;
                }
            }

            if (isConsecutive) {
                validSlices.add(new ArrayList<>(list.subList(i, i + size)));
            }
        }

        if (validSlices.isEmpty()) {
            throw new IllegalArgumentException("Can't generate timeframe of size " + size + " hours");
        }

        return validSlices.get(random.nextInt(validSlices.size()));
    }
}
