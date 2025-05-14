//package com.example.schedule_composer.schedule_generator;
//
//import com.example.schedule_composer.entity.*;
//import com.example.schedule_composer.service.*;
//import com.example.schedule_composer.utils.RandomUtils;
//import com.example.schedule_composer.utils.types.RoomType;
//import com.example.schedule_composer.utils.types.TeachingMode;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.time.DayOfWeek;
//import java.util.Collections;
//import java.util.List;
//import java.util.Random;
//import java.util.concurrent.ThreadLocalRandom;
//
//@Component
//public class Generator {
//
//    private final TimeFrameManager timeFrameManager;
//    private final CourseService courseService;
//    private final GroupService groupService;
//    private final RoomService roomService;
//    private final TeacherService teacherService;
//
//    private final ScheduleItemService scheduleService;
//    private final ScheduleSharedItemService scheduleSharedService;
//    private final ScheduleLunchItemService scheduleLunchService;
//    private final SetupService setupService;
//    private final SetupSharedService setupSharedService;
//
//    @Autowired
//    public Generator(
//            TimeFrameManager timeFrameManager,
//            CourseService courseService,
//            GroupService groupService,
//            RoomService roomService,
//            TeacherService teacherService,
//            ScheduleItemService scheduleService,
//            ScheduleSharedItemService scheduleSharedService,
//            ScheduleLunchItemService scheduleLunchService,
//            SetupService setupService,
//            SetupSharedService setupSharedService
//            ) {
//        this.timeFrameManager = timeFrameManager;
//        this.courseService = courseService;
//        this.groupService = groupService;
//        this.roomService = roomService;
//        this.teacherService = teacherService;
//        this.scheduleService = scheduleService;
//        this.scheduleSharedService = scheduleSharedService;
//        this.scheduleLunchService = scheduleLunchService;
//        this.setupService = setupService;
//        this.setupSharedService = setupSharedService;
//    }
//
//    public void generate() {
//        List<Setup> setups = setupService.getAllEntities();
//        List<SetupShared> setupsShared = setupSharedService.getAllEntities();
//
//        List<Room> labRooms = roomService.getAllEntities();
//        List<Room> classRooms = roomService.getAllEntities();
//
//        Collections.shuffle(setups);
//        Collections.shuffle(setupsShared);
//        Collections.shuffle(labRooms);
//        Collections.shuffle(classRooms);
//
//        // Attempting to distribute SetupShared items
//
//        for(SetupShared currSetupShared : setupsShared) {
//            boolean canBeAdded = true;
//            DayOfWeek randomDayOfWeek;
//            List<TimeSlot> randomtimeFrame;
//            for (int i = 0; i < 20; i++){
//                randomDayOfWeek = getRandomWeekday();
//                randomtimeFrame = timeFrameManager.getRandomTimeSlotsSet(
//                        RandomUtils.randomFromOneTo(currSetupShared.getHoursAWeek()));
//                List<Room> suitableRooms = currSetupShared.getPreferredRoomType() == RoomType.LAB ? labRooms : classRooms;
//                Room randomRoom = getRandomRoom(suitableRooms);
//
//                boolean areTimeFramesCrossing = false;
//                boolean isTeacherSame = false;
//                boolean isRoomSame = false;
//
//                // Checking each SetupShared item with the ScheduleSharedItem items
//
//                for(ScheduleSharedItem scheduleSharedItem : scheduleSharedService.getAllEntities()){
//
//                    isTeacherSame = scheduleSharedItem.getSetupShared().getTeacher().getId() == currSetupShared.getTeacher().getId();
//                    isRoomSame = randomRoom.getId() == scheduleSharedItem.getRoom().getId();
//
//                    for(TimeSlot scheduleSharedTimeSlot : scheduleSharedItem.getTimeSlots()) {
//
//                        if(scheduleSharedItem.getDay() == randomDayOfWeek){
//
//                            // Checking if timeframes are crossing
//
//                            for(TimeSlot timeSlot : randomtimeFrame) {
//                                if(timeSlot.getEndTime().equals(scheduleSharedTimeSlot.getStartTime())
//                                        || timeSlot.getEndTime().equals(scheduleSharedTimeSlot.getEndTime())
//                                        || (timeSlot.getStartTime().isBefore(scheduleSharedTimeSlot.getEndTime()) && timeSlot.getStartTime().isAfter(scheduleSharedTimeSlot.getStartTime()))
//                                        || (timeSlot.getEndTime().isBefore(scheduleSharedTimeSlot.getEndTime()) && timeSlot.getEndTime().isAfter(scheduleSharedTimeSlot.getStartTime()))
//                                ){
//                                    areTimeFramesCrossing = true;
//                                    break;
//                                }
//                            }
//
//                            if(areTimeFramesCrossing){
//                                if(isTeacherSame || (isRoomSame)){
//                                    canBeAdded = false;
//                                    break;
//                                }
//                            }
//                        }
//                    }
//                }
//
//                // Checking each SetupShared item with the ScheduleItem items
//
//                for(ScheduleItem scheduleItem : scheduleService.getAllEntities()){
//
//                    isTeacherSame = scheduleItem.getSetup().getTeacher().getId() == currSetupShared.getTeacher().getId();
//                    isRoomSame = randomRoom.getId() == scheduleItem.getRoom().getId();
//
//                    for(TimeSlot scheduleTimeSlot : scheduleItem.getTimeSlots()) {
//
//                        if(scheduleItem.getDay() == randomDayOfWeek){
//
//                            // Checking if timeframes are crossing
//
//                            for(TimeSlot timeSlot : randomtimeFrame) {
//                                if(timeSlot.getEndTime().equals(scheduleTimeSlot.getStartTime())
//                                        || timeSlot.getEndTime().equals(scheduleTimeSlot.getEndTime())
//                                        || (timeSlot.getStartTime().isBefore(scheduleTimeSlot.getEndTime()) && timeSlot.getStartTime().isAfter(scheduleTimeSlot.getStartTime()))
//                                        || (timeSlot.getEndTime().isBefore(scheduleTimeSlot.getEndTime()) && timeSlot.getEndTime().isAfter(scheduleTimeSlot.getStartTime()))
//                                ){
//                                    areTimeFramesCrossing = true;
//                                    break;
//                                }
//                            }
//
//                            if(areTimeFramesCrossing){
//                                if(isTeacherSame || isRoomSame){
//                                    canBeAdded = false;
//                                    break;
//                                }
//                            }
//                        }
//                    }
//                }
//
//                if(canBeAdded){
//                    ScheduleSharedItem newItem = ScheduleSharedItem.builder()
//                            .setupShared(currSetupShared)
//                            .room(randomRoom)
//                            .day(randomDayOfWeek)
//                            .timeSlots(randomtimeFrame)
//                            .teachingMode(TeachingMode.CLASSROOM)
//                            .build();
//                    scheduleSharedService.create(newItem);
//                    currSetupShared.setHoursAWeek(currSetupShared.getHoursAWeek() - randomtimeFrame.size());
//                }
//            }
//        }
//
//
//    }
//
//    public static DayOfWeek getRandomWeekday() {
//        int randomDayOfWeekInt = ThreadLocalRandom.current().nextInt(1, 6);
//
//        return DayOfWeek.of(randomDayOfWeekInt);
//    }
//
//    public static Room getRandomRoom(List<Room> rooms) {
//
//        Room randomRoom = null;
//
//        if (!rooms.isEmpty()) {
//            Random random = new Random();
//            randomRoom = rooms.get(random.nextInt(rooms.size()));
//        } else {
//            System.out.println("No lab rooms available.");
//        }
//
//        return randomRoom;
//    }
//
//}
