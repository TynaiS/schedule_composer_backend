//package com.example.schedule_composer.schedule_generator;
//
//import com.example.schedule_composer.entity.CourseGroupTeacher;
//import com.example.schedule_composer.entity.Room;
//import com.example.schedule_composer.entity.Schedule;
//import com.example.schedule_composer.service.*;
//import com.example.schedule_composer.utils.CourseType;
//import com.example.schedule_composer.utils.RoomType;
//import com.example.schedule_composer.utils.TeachingMode;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.time.DayOfWeek;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Random;
//import java.util.concurrent.ThreadLocalRandom;
//
//@Component
//public class Generator {
//
//    private final CourseService courseService;
//    private final GroupService groupService;
//    private final RoomService roomService;
//    private final TeacherService teacherService;
//    private final ScheduleService scheduleService;
//    private final ScheduleLunchService scheduleLunchService;
//    private final CourseGroupTeacherService courseGroupTeacherService;
//
//    @Autowired
//    public Generator(
//            CourseService courseService,
//            GroupService groupService,
//            RoomService roomService,
//            TeacherService teacherService,
//            ScheduleService scheduleService,
//            ScheduleLunchService scheduleLunchService,
//            CourseGroupTeacherService courseGroupTeacherService
//            ) {
//        this.courseService = courseService;
//        this.groupService = groupService;
//        this.roomService = roomService;
//        this.teacherService = teacherService;
//        this.scheduleService = scheduleService;
//        this.scheduleLunchService = scheduleLunchService;
//        this.courseGroupTeacherService = courseGroupTeacherService;
//    }
//
//    public void generate() {
//        List<CourseGroupTeacher> courseGroupTeachers = courseGroupTeacherService.getCourseGroupTeachers();
//
//        List<CourseGroupTeacher> obligatoryCourseGroupTeachers = courseGroupTeacherService.getObligatoryCourseGroupTeachers();
//        List<CourseGroupTeacher> electiveCourseGroupTeachers = courseGroupTeacherService.getElectiveCourseGroupTeachers();
//
//        List<Room> labRooms = roomService.getLabs();
//        List<Room> classRooms = roomService.getRooms();
//
//        Collections.shuffle(obligatoryCourseGroupTeachers);
//        Collections.shuffle(electiveCourseGroupTeachers);
//        Collections.shuffle(labRooms);
//        Collections.shuffle(classRooms);
//
//        for(CourseGroupTeacher currCourseGroupTeacher : obligatoryCourseGroupTeachers) {
//            boolean canBeAdded = true;
//            DayOfWeek dayOfWeek;
//            RandomTimeFramePair.TimeFrame randomtimeFrame;
//            for (int i = 0; i < 10; i++){
//                dayOfWeek = getRandomWeekday();
//                randomtimeFrame = RandomTimeFramePair.getRandomTimeFramePair(1);
//                List<Room> suitableRooms = currCourseGroupTeacher.getRequiredRoomType() == RoomType.LAB ? labRooms : classRooms;
//                Room randomRoom = getRandomRoom(suitableRooms);
//
//
//                for(Schedule schedule_item : scheduleService.getSchedules()){
//                    boolean areTimeFramesCrossing = !(randomtimeFrame.endTime.isBefore(schedule_item.getStartTime()) || schedule_item.getEndTime().isBefore(randomtimeFrame.startTime));
//                    boolean isTeacherSame = schedule_item.getCourseGroupTeacher().getTeacher().getId() == currCourseGroupTeacher.getTeacher().getId();
//                    boolean isRoomSame = randomRoom.getId() == schedule_item.getRoom().getId();
//
//                    if(schedule_item.getDay() == dayOfWeek){
//                        if(areTimeFramesCrossing){
//                            if(isTeacherSame || (isRoomSame && schedule_item.getTeachingMode() == TeachingMode.CLASSROOM)){
//                                canBeAdded = false;
//                                break;
//                            }
//                        }
//                    }
//                }
//
//                if(canBeAdded){
//                    scheduleService.createScheduleItem(currCourseGroupTeacher, randomRoom, dayOfWeek, randomtimeFrame.startTime, randomtimeFrame.endTime, TeachingMode.CLASSROOM);
//                }
//            }
//        }
//
//
//    }
//
//    public static DayOfWeek getRandomWeekday() {
//        int dayOfWeekInt = ThreadLocalRandom.current().nextInt(1, 6);
//
//        return DayOfWeek.of(dayOfWeekInt);
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
