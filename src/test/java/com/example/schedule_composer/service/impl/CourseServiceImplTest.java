//package com.example.schedule_composer.service.impl;
//
//import com.example.schedule_composer.dto.get.CourseDTOGet;
//import com.example.schedule_composer.dto.patch.CourseDTOPatch;
//import com.example.schedule_composer.dto.post.CourseDTOPost;
//import com.example.schedule_composer.entity.Course;
//import com.example.schedule_composer.entity.Schedule;
//import com.example.schedule_composer.mappers.CourseMapper;
//import com.example.schedule_composer.repository.CourseRepository;
//import com.example.schedule_composer.service.ScheduleService;
//import jakarta.persistence.EntityNotFoundException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.*;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class CourseServiceImplTest {
//
//    @Mock
//    private CourseRepository courseRepository;
//
//    @Mock
//    private CourseMapper courseMapper;
//
//    @Mock
//    private ScheduleService scheduleService;
//
//    @InjectMocks
//    private CourseServiceImpl courseService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testGetById() {
//        Long courseId = 1L;
//        Course courseEntity = Course.builder().id(courseId).build();
//        CourseDTOGet dtoGet = CourseDTOGet.builder().id(courseId).name("Test Course").build();
//
//        when(courseRepository.findById(courseId)).thenReturn(Optional.of(courseEntity));
//        when(courseMapper.fromEntityToGet(courseEntity)).thenReturn(dtoGet);
//
//        CourseDTOGet result = courseService.getById(courseId);
//
//        assertThat(result).isEqualTo(dtoGet);
//        verify(courseRepository).findById(courseId);
//        verify(courseMapper).fromEntityToGet(courseEntity);
//    }
//
//    @Test
//    void testCreate() {
//        CourseDTOPost postDto = new CourseDTOPost("New Course", 2);
//        Course courseEntity = Course.builder().name("New Course").build();
//        Course savedEntity = Course.builder().id(10L).name("New Course").build();
//        CourseDTOGet dtoGet = CourseDTOGet.builder().id(10L).name("New Course").build();
//
//        when(courseMapper.fromPostToEntity(postDto)).thenReturn(courseEntity);
//        when(courseRepository.save(courseEntity)).thenReturn(savedEntity);
//        when(courseMapper.fromEntityToGet(savedEntity)).thenReturn(dtoGet);
//
//        CourseDTOGet result = courseService.create(postDto);
//
//        assertThat(result).isEqualTo(dtoGet);
//        verify(courseMapper).fromPostToEntity(postDto);
//        verify(courseRepository).save(courseEntity);
//        verify(courseMapper).fromEntityToGet(savedEntity);
//    }
//
//    @Test
//    void testUpdate() {
//        Long courseId = 1L;
//        CourseDTOPatch patchDto = new CourseDTOPatch("Updated course", 2);
//        Course existingEntity = Course.builder().id(courseId).name("Old Name").build();
//        Course patchedEntity = Course.builder().id(courseId).name("Updated Name").build();
//        Course savedEntity = patchedEntity;
//        CourseDTOGet dtoGet = CourseDTOGet.builder().id(courseId).name("Updated Name").build();
//
//        when(courseRepository.findById(courseId)).thenReturn(Optional.of(existingEntity));
//        when(courseMapper.fromPatchToEntity(patchDto, existingEntity)).thenReturn(patchedEntity);
//        when(courseRepository.save(patchedEntity)).thenReturn(savedEntity);
//        when(courseMapper.fromEntityToGet(savedEntity)).thenReturn(dtoGet);
//
//        CourseDTOGet result = courseService.update(courseId, patchDto);
//
//        assertThat(result).isEqualTo(dtoGet);
//        verify(courseRepository).findById(courseId);
//        verify(courseMapper).fromPatchToEntity(patchDto, existingEntity);
//        verify(courseRepository).save(patchedEntity);
//        verify(courseMapper).fromEntityToGet(savedEntity);
//    }
//
//    @Test
//    void testDeleteById_exists() {
//        Long courseId = 1L;
//
//        when(courseRepository.existsById(courseId)).thenReturn(true);
//        doNothing().when(courseRepository).deleteById(courseId);
//
//        courseService.deleteById(courseId);
//
//        verify(courseRepository).existsById(courseId);
//        verify(courseRepository).deleteById(courseId);
//    }
//
//    @Test
//    void testDeleteById_notExists_throws() {
//        Long courseId = 1L;
//
//        when(courseRepository.existsById(courseId)).thenReturn(false);
//
//        assertThatThrownBy(() -> courseService.deleteById(courseId))
//                .isInstanceOf(EntityNotFoundException.class)
//                .hasMessageContaining("Course not found with id: " + courseId);
//
//        verify(courseRepository).existsById(courseId);
//        verify(courseRepository, never()).deleteById(any());
//    }
//
//    @Test
//    void testGetAll() {
//        List<Course> courses = List.of(
//                Course.builder().id(1L).build(),
//                Course.builder().id(2L).build()
//        );
//        List<CourseDTOGet> dtos = List.of(
//                CourseDTOGet.builder().id(1L).name("Course 1").build(),
//                CourseDTOGet.builder().id(2L).name("Course 2").build()
//        );
//
//        when(courseRepository.findAll()).thenReturn(courses);
//        when(courseMapper.fromEntityListToGetList(courses)).thenReturn(dtos);
//
//        List<CourseDTOGet> result = courseService.getAll();
//
//        assertThat(result).isEqualTo(dtos);
//        verify(courseRepository).findAll();
//        verify(courseMapper).fromEntityListToGetList(courses);
//    }
//
//    @Test
//    void testGetByIdForUser() {
//        Long userId = 5L;
//        Long courseId = 10L;
//        Course courseEntity = Course.builder().id(courseId).build();
//        CourseDTOGet dtoGet = CourseDTOGet.builder().id(courseId).name("User Course").build();
//
//        when(courseRepository.findById(courseId)).thenReturn(Optional.of(courseEntity));
//        doNothing().when(scheduleService).checkUserAccessToSchedule(courseEntity.getSchedule(), userId);
//        when(courseMapper.fromEntityToGet(courseEntity)).thenReturn(dtoGet);
//
//        // Mock getEntityByIdForUserSchedule to use real method, so:
//        // Since getEntityByIdForUserSchedule calls getEntityById and scheduleService.checkUserAccessToSchedule,
//        // We can spy on courseService or simulate it:
//
//        CourseServiceImpl spyService = Mockito.spy(courseService);
//        doReturn(courseEntity).when(spyService).getEntityById(courseId);
//        doNothing().when(scheduleService).checkUserAccessToSchedule(courseEntity.getSchedule(), userId);
//        doReturn(dtoGet).when(courseMapper).fromEntityToGet(courseEntity);
//
//        CourseDTOGet result = spyService.getByIdForUser(userId, courseId);
//
//        assertThat(result).isEqualTo(dtoGet);
//        verify(scheduleService).checkUserAccessToSchedule(courseEntity.getSchedule(), userId);
//        verify(courseMapper).fromEntityToGet(courseEntity);
//    }
//
//    @Test
//    void testCreateForUserSchedule() {
//        Long userId = 7L;
//        Long scheduleId = 3L;
//
//        Schedule schedule = Schedule.builder().id(scheduleId).build();
//
//        CourseDTOPost postRequest = new CourseDTOPost("User Course Creation", 2);
//
//
//        Course courseEntity = Course.builder().name("User Course Creation").build();
//        Course savedCourse = Course.builder().id(100L).name("User Course Creation").schedule(schedule).build();
//        CourseDTOGet dtoGet = CourseDTOGet.builder().id(100L).name("User Course Creation").build();
//
//        when(scheduleService.getEntityByIdForUser(userId, scheduleId)).thenReturn(schedule);
//        when(courseMapper.fromPostToEntity(postRequest)).thenReturn(courseEntity);
//        when(courseRepository.save(courseEntity)).thenReturn(savedCourse);
//        when(courseMapper.fromEntityToGet(savedCourse)).thenReturn(dtoGet);
//
//        CourseDTOGet result = courseService.createForUserSchedule(userId, scheduleId, postRequest);
//
//        assertThat(result).isEqualTo(dtoGet);
//        verify(scheduleService).getEntityByIdForUser(userId, scheduleId);
//        verify(courseMapper).fromPostToEntity(postRequest);
//        verify(courseRepository).save(courseEntity);
//        verify(courseMapper).fromEntityToGet(savedCourse);
//    }
//
//    @Test
//    void testUpdateForUser() {
//        Long userId = 7L;
//        Long courseId = 8L;
//
//        CourseDTOPatch patchRequest = new CourseDTOPatch("User Course Patch", 2);
//
//        Schedule schedule = Schedule.builder().id(1L).build();
//        Course existingCourse = Course.builder().id(courseId).name("Old Name").schedule(schedule).build();
//        Course patchedCourse = Course.builder().id(courseId).name("User Course Patch").schedule(schedule).build();
//        CourseDTOGet dtoGet = CourseDTOGet.builder().id(courseId).name("User Course Patch").build();
//
//        CourseServiceImpl spyService = Mockito.spy(courseService);
//        doReturn(existingCourse).when(spyService).getEntityByIdForUserSchedule(userId, courseId);
//
//        when(courseMapper.fromPatchToEntity(patchRequest, existingCourse)).thenReturn(patchedCourse);
//        when(courseRepository.save(patchedCourse)).thenReturn(patchedCourse);
//        when(courseMapper.fromEntityToGet(patchedCourse)).thenReturn(dtoGet);
//
//        CourseDTOGet result = spyService.updateForUser(userId, courseId, patchRequest);
//
//        assertThat(result).isEqualTo(dtoGet);
//        verify(courseMapper).fromPatchToEntity(patchRequest, existingCourse);
//        verify(courseRepository).save(patchedCourse);
//        verify(courseMapper).fromEntityToGet(patchedCourse);
//    }
//
//    @Test
//    void testDeleteByIdForUser() {
//        Long userId = 7L;
//        Long courseId = 9L;
//
//        Schedule schedule = Schedule.builder().id(1L).build();
//        Course course = Course.builder().id(courseId).schedule(schedule).build();
//
//        CourseServiceImpl spyService = Mockito.spy(courseService);
//        doReturn(course).when(spyService).getEntityByIdForUserSchedule(userId, courseId);
//
//        doNothing().when(courseRepository).delete(course);
//
//        spyService.deleteByIdForUser(userId, courseId);
//
//        verify(courseRepository).delete(course);
//    }
//
//    @Test
//    void testGetAllEntitiesForUserSchedule() {
//        Long userId = 10L;
//        Long scheduleId = 11L;
//
//        Schedule schedule = Schedule.builder().id(scheduleId).build();
//        List<Course> courses = List.of(
//                Course.builder().id(1L).schedule(schedule).build(),
//                Course.builder().id(2L).schedule(schedule).build()
//        );
//
//        when(scheduleService.getEntityByIdForUser(userId, scheduleId)).thenReturn(schedule);
//        when(courseRepository.findAllByScheduleId(scheduleId)).thenReturn(courses);
//
//        List<Course> result = courseService.getAllEntitiesForUserSchedule(userId, scheduleId);
//
//        assertThat(result).isEqualTo(courses);
//        verify(scheduleService).getEntityByIdForUser(userId, scheduleId);
//        verify(courseRepository).findAllByScheduleId(scheduleId);
//    }
//}
