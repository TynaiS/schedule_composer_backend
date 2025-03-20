//package com.example.schedule_composer.schedule_generator;
//
//import com.example.schedule_composer.entity.TimeSlot;
//import com.example.schedule_composer.repository.TimeSlotRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.sql.Time;
//import java.time.LocalTime;
//import java.util.List;
//import java.util.Random;
//
//@Component
//public class RandomTimeFramePair {
//
//    private final TimeSlotRepository timeSlotRepository;
//
//    @Autowired
//    public RandomTimeFramePair(TimeSlotRepository timeSlotRepository){
//        this.timeSlotRepository = timeSlotRepository;
//    }
//
//    public TimeSlot getRandomTimeFramePair(int academicHours) {
//        List<TimeSlot> timeSlots = timeSlotRepository.findAll();
//
//
//        TimeSlot[] timeFrames =
//                switch (academicHours) {
//                    case 1:
//                        yield getOneAcademicHourTimeFrames();
//                    case 2:
//                        yield getTwoAcademicHourTimeFrames();
//                    case 3:
//                        yield getThreeAcademicHourTimeFrames();
//                    case 4:
//                        yield getFourAcademicHourTimeFrames();
//                    default:
//                        yield getOneAcademicHourTimeFrames();
//                };
//
//
//        Random random = new Random();
//        int randomIndex = random.nextInt(timeFrames.length);
//
//        TimeFrame randomTimeFrame = timeFrames[randomIndex];
//
//        return randomTimeFrame;
//    }
//
//
//
//    private static TimeFrame[] getOneAcademicHourTimeFrames() {
//        return null;
//    }
//
//    private static TimeFrame[] getTwoAcademicHourTimeFrames() {
//        return null;
//    }
//
//    private static TimeFrame[] getThreeAcademicHourTimeFrames() {
//        return null;
//    }
//
//    private static TimeFrame[] getFourAcademicHourTimeFrames() {
//        return null;
//    }
//}
