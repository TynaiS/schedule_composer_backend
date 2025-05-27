package com.example.schedule_composer.schedule_generator;

import com.example.schedule_composer.dto.get.GroupDTOGet;
import com.example.schedule_composer.dto.get.SetupItemDTOGet;
import com.example.schedule_composer.dto.get.SetupSharedItemDTOGet;
import com.example.schedule_composer.entity.*;
import com.example.schedule_composer.service.*;
import com.example.schedule_composer.utils.RandomUtils;
import com.example.schedule_composer.utils.types.RoomType;
import com.example.schedule_composer.utils.types.TeachingMode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Generator {

    private final TimeFrameManager timeFrameManager;
    private final TimeSlotService timeSlotService;
    private final CourseService courseService;
    private final GroupService groupService;
    private final RoomService roomService;
    private final TeacherService teacherService;

    private final ScheduleVersionService scheduleVersionService;
    private final ScheduleItemService scheduleService;
    private final ScheduleSharedItemService scheduleSharedService;
    private final ScheduleLunchItemService scheduleLunchService;
    private final SetupItemService setupItemService;
    private final SetupSharedItemService setupSharedItemService;


    public void generate(Long userId, Long scheduleVersionId) {
        ScheduleVersion scheduleVersion = scheduleVersionService.getEntityByIdForUser(userId, scheduleVersionId);
        Schedule schedule = scheduleVersion.getSchedule();
        List<SetupItemDTOGet> setupItemsDTO = setupItemService.getAllForUserScheduleVersion(userId, scheduleVersion.getId());
        List<SetupSharedItemDTOGet> setupSharedItemsDTO = setupSharedItemService.getAllForUserScheduleVersion(userId, scheduleVersion.getId());

        List<Room> labRooms = roomService.getAllEntities();
        List<Room> classRooms = roomService.getAllEntities();

        Collections.shuffle(setupItemsDTO);
        Collections.shuffle(setupSharedItemsDTO);
        Collections.shuffle(labRooms);
        Collections.shuffle(classRooms);



        // Attempting to create ScheduleLunchItem entities

        List<TimeSlot> timeSlotsForLunches = timeSlotService.getAllEntitiesWithLunchAllowedForUserSchedule(userId, schedule.getId());

        for (DayOfWeek day : DayOfWeek.values()) {
            for(Group group : groupService.getAllEntitiesForUserSchedule(userId, schedule.getId())) {

                if (day.getValue() >= 1 && day.getValue() <= 5) {
                    TimeSlot randomLunchTimeSlot = timeSlotsForLunches.get(RandomUtils.randomFromOneTo(timeSlotsForLunches.size()));
                    List<TimeSlot> randomLunchTimeSlotList = List.of(randomLunchTimeSlot);
                    ScheduleLunchItem newLunchItem = ScheduleLunchItem.builder()
                            .scheduleVersion(scheduleVersion)
                            .group(group)
                            .day(day)
                            .timeSlots(randomLunchTimeSlotList)
                            .build();
                    scheduleLunchService.create(newLunchItem);
                }

            }
        }




        // Attempting to distribute SetupSharedItem entities

        for(SetupSharedItemDTOGet currSetupSharedItemDTO : setupSharedItemsDTO) {
            boolean canBeAdded = true;
            DayOfWeek randomDayOfWeek;
            List<TimeSlot> randomtimeFrame;

            outerLoop:
            for (int i = 0; i < 20; i++){
                randomtimeFrame = timeFrameManager.getRandomTimeSlotsSet(userId, schedule.getId(),
                        RandomUtils.randomFromOneTo(currSetupSharedItemDTO.getSetupSharedSet().getHoursAWeek()));

                if(randomtimeFrame.size() == 0) break;

                randomDayOfWeek = getRandomWeekday();
                List<Room> suitableRooms = currSetupSharedItemDTO.getPreferredRoomType() == RoomType.LAB ? labRooms : classRooms;
                Room randomRoom = getRandomRoom(suitableRooms);

                boolean areTimeFramesCrossing = false;
                boolean isTeacherSame = false;
                boolean isRoomSame = false;
                boolean haveCommonGroups = false;

                // Checking current SetupSharedItem with the ScheduleLunchItems

                for(ScheduleLunchItem scheduleLunchItem : scheduleLunchService.getAllEntitiesForUserScheduleVersion(userId, scheduleVersionId)){
                    haveCommonGroups = checkForCommonGroups(currSetupSharedItemDTO.getGroups(), List.of(scheduleLunchItem.getGroup()));

                    if(scheduleLunchItem.getDay() == randomDayOfWeek) {
                        areTimeFramesCrossing = checkIfTimeFramesAreCrossing(randomtimeFrame, scheduleLunchItem.getTimeSlots());
                    }

                    if(areTimeFramesCrossing){
                        if(haveCommonGroups){
                            canBeAdded = false;
                            break outerLoop;
                        }
                    }
                }


                // Checking current SetupSharedItem with the ScheduleSharedItems

                for(ScheduleSharedItem scheduleSharedItem : scheduleSharedService.getAllEntitiesForUserScheduleVersion(userId, scheduleVersionId)){

                    isTeacherSame = scheduleSharedItem.getSetupSharedItem().getTeacher().getId() == currSetupSharedItemDTO.getTeacher().getId();
                    isRoomSame = randomRoom.getId() == scheduleSharedItem.getRoom().getId();
                    haveCommonGroups = checkForCommonGroups(currSetupSharedItemDTO.getGroups(), scheduleSharedItem.getSetupSharedItem().getGroups());

                    if(scheduleSharedItem.getDay() == randomDayOfWeek) {
                        checkIfTimeFramesAreCrossing(randomtimeFrame, scheduleSharedItem.getTimeSlots());
                    }


                    if(areTimeFramesCrossing){
                        if(isTeacherSame || isRoomSame || haveCommonGroups){
                            canBeAdded = false;
                            break outerLoop;
                        }
                    }
                }

                if(canBeAdded){
                    SetupSharedItem setupSharedItem = setupSharedItemService.getEntityById(currSetupSharedItemDTO.getId());
                    ScheduleSharedItem newItem = ScheduleSharedItem.builder()
                            .setupSharedItem(setupSharedItem)
                            .room(randomRoom)
                            .day(randomDayOfWeek)
                            .timeSlots(randomtimeFrame)
                            .teachingMode(TeachingMode.CLASSROOM)
                            .build();
                    scheduleSharedService.create(newItem);
                    currSetupSharedItemDTO.getSetupSharedSet().setHoursAWeek(
                            currSetupSharedItemDTO.getSetupSharedSet().getHoursAWeek() - randomtimeFrame.size());
                }
            }
        }


        // Attempting to distribute SetupItem entities

        for(SetupItemDTOGet currSetupItemDTO : setupItemsDTO) {
            boolean canBeAdded = true;
            DayOfWeek randomDayOfWeek;
            List<TimeSlot> randomtimeFrame;

            outerLoop:
            for (int i = 0; i < 20; i++){
                randomtimeFrame = timeFrameManager.getRandomTimeSlotsSet(userId, schedule.getId(),
                        RandomUtils.randomFromOneTo(currSetupItemDTO.getHoursAWeek()));

                if(randomtimeFrame.size() == 0) break;

                randomDayOfWeek = getRandomWeekday();
                List<Room> suitableRooms = currSetupItemDTO.getPreferredRoomType() == RoomType.LAB ? labRooms : classRooms;
                Room randomRoom = getRandomRoom(suitableRooms);

                boolean areTimeFramesCrossing = false;
                boolean isTeacherSame = false;
                boolean isRoomSame = false;
                boolean haveCommonGroups = false;

                // Checking current SetupSharedItem with the ScheduleLunchItems

                for(ScheduleLunchItem scheduleLunchItem : scheduleLunchService.getAllEntitiesForUserScheduleVersion(userId, scheduleVersionId)){
                    haveCommonGroups = checkForCommonGroups(List.of(currSetupItemDTO.getGroup()), List.of(scheduleLunchItem.getGroup()));

                    if(scheduleLunchItem.getDay() == randomDayOfWeek) {
                        areTimeFramesCrossing = checkIfTimeFramesAreCrossing(randomtimeFrame, scheduleLunchItem.getTimeSlots());
                    }

                    if(areTimeFramesCrossing){
                        if(haveCommonGroups){
                            canBeAdded = false;
                            break outerLoop;
                        }
                    }
                }

                // Checking current SetupItem item with the ScheduleSharedItems

                for(ScheduleSharedItem scheduleSharedItem : scheduleSharedService.getAllEntitiesForUserScheduleVersion(userId, scheduleVersionId)){

                    isTeacherSame = scheduleSharedItem.getSetupSharedItem().getTeacher().getId() == currSetupItemDTO.getTeacher().getId();
                    isRoomSame = randomRoom.getId() == scheduleSharedItem.getRoom().getId();
                    haveCommonGroups = checkForCommonGroups(List.of(currSetupItemDTO.getGroup()), scheduleSharedItem.getSetupSharedItem().getGroups());

                    if(scheduleSharedItem.getDay() == randomDayOfWeek) {
                        areTimeFramesCrossing = checkIfTimeFramesAreCrossing(randomtimeFrame, scheduleSharedItem.getTimeSlots());
                    }


                    if(areTimeFramesCrossing){
                        if(isTeacherSame || isRoomSame || haveCommonGroups){
                            canBeAdded = false;
                            break outerLoop;
                        }
                    }
                }



                // Checking current SetupItem item with the ScheduleSharedItems

                for(ScheduleItem scheduleItem : scheduleService.getAllEntities()){

                    isTeacherSame = scheduleItem.getSetupItem().getTeacher().getId() == currSetupItemDTO.getTeacher().getId();
                    isRoomSame = randomRoom.getId() == scheduleItem.getRoom().getId();
                    haveCommonGroups = currSetupItemDTO.getGroup().getId() == scheduleItem.getSetupItem().getGroup().getId();

                    if(scheduleItem.getDay() == randomDayOfWeek) {
                        areTimeFramesCrossing = checkIfTimeFramesAreCrossing(randomtimeFrame, scheduleItem.getTimeSlots());
                    }

                    if(areTimeFramesCrossing){
                        if(isTeacherSame || isRoomSame || haveCommonGroups){
                            canBeAdded = false;
                            break outerLoop;
                        }
                    }

                }

                if(canBeAdded){
                    SetupSharedItem setupSharedItem = setupSharedItemService.getEntityById(currSetupItemDTO.getId());
                    ScheduleSharedItem newItem = ScheduleSharedItem.builder()
                            .setupSharedItem(setupSharedItem)
                            .room(randomRoom)
                            .day(randomDayOfWeek)
                            .timeSlots(randomtimeFrame)
                            .teachingMode(TeachingMode.CLASSROOM)
                            .build();
                    scheduleSharedService.create(newItem);
                    currSetupItemDTO.setHoursAWeek(
                            currSetupItemDTO.getHoursAWeek() - randomtimeFrame.size());
                }
            }
        }



    }

    public static DayOfWeek getRandomWeekday() {
        int randomDayOfWeekInt = ThreadLocalRandom.current().nextInt(1, 6);

        return DayOfWeek.of(randomDayOfWeekInt);
    }

    public static Room getRandomRoom(List<Room> rooms) {

        Room randomRoom = null;

        if (!rooms.isEmpty()) {
            Random random = new Random();
            randomRoom = rooms.get(random.nextInt(rooms.size()));
        } else {
            System.out.println("No lab rooms available.");
        }

        return randomRoom;
    }

    public static boolean checkIfTimeFramesAreCrossing(List<TimeSlot> setupTimeFrame, List<TimeSlot> scheduleTimeFrame){
        boolean areCrossing = false;

        for(TimeSlot scheduleTimeSlot : scheduleTimeFrame) {
            for(TimeSlot timeSlot : setupTimeFrame) {
                if(timeSlot.getEndTime().equals(scheduleTimeSlot.getStartTime())
                        || timeSlot.getEndTime().equals(scheduleTimeSlot.getEndTime())
                        || (timeSlot.getStartTime().isBefore(scheduleTimeSlot.getEndTime()) && timeSlot.getStartTime().isAfter(scheduleTimeSlot.getStartTime()))
                        || (timeSlot.getEndTime().isBefore(scheduleTimeSlot.getEndTime()) && timeSlot.getEndTime().isAfter(scheduleTimeSlot.getStartTime()))
                ){
                    areCrossing = true;
                    break;
                }
            }
        }

        return areCrossing;
    }

    public boolean checkForCommonGroups(List<GroupDTOGet> list1, List<Group> list2) {
        Set<Long> ids = list1.stream()
                .map(GroupDTOGet::getId)
                .collect(Collectors.toSet());

        return list2.stream().anyMatch(group -> ids.contains(group.getId()));
    }

}
