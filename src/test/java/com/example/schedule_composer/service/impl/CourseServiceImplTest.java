package com.example.schedule_composer.service.impl;

import com.example.schedule_composer.dto.get.CourseDTOGet;
import com.example.schedule_composer.dto.patch.CourseDTOPatch;
import com.example.schedule_composer.dto.post.CourseDTOPost;
import com.example.schedule_composer.entity.Course;
import com.example.schedule_composer.entity.Schedule;
import com.example.schedule_composer.mappers.CourseMapper;
import com.example.schedule_composer.repository.CourseRepository;
import com.example.schedule_composer.service.ScheduleService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {

    @InjectMocks
    private CourseServiceImpl courseService;

    @Mock
    private CourseRepository courseRepository;
    @Mock
    private CourseMapper courseMapper;
    @Mock
    private ScheduleService scheduleService;


    private final Long COURSE_ID = 1L;
    private final Long SCHEDULE_ID = 2L;
    private final Long USER_ID = 3L;

    @Test
    void getById_returnsDTO() {
        Course course = new Course();
        CourseDTOGet dto = CourseDTOGet.builder().id(COURSE_ID).build();

        when(courseRepository.findById(COURSE_ID)).thenReturn(Optional.of(course));
        when(courseMapper.fromEntityToGet(course)).thenReturn(dto);

        CourseDTOGet result = courseService.getById(COURSE_ID);

        assertThat(result).isEqualTo(dto);
    }

    @Test
    void create_savesEntityAndReturnsDTO() {
        CourseDTOPost post = CourseDTOPost.builder().name("Math").credits(3).build();
        Course entity = new Course();
        Course saved = new Course();
        CourseDTOGet dto = CourseDTOGet.builder().id(COURSE_ID).build();

        when(courseMapper.fromPostToEntity(post)).thenReturn(entity);
        when(courseRepository.save(entity)).thenReturn(saved);
        when(courseMapper.fromEntityToGet(saved)).thenReturn(dto);

        CourseDTOGet result = courseService.create(post);

        assertThat(result).isEqualTo(dto);
    }

    @Test
    void update_updatesAndReturnsDTO() {
        Course existing = new Course();
        CourseDTOPatch patch = CourseDTOPatch.builder().name("Physics").credits(4).build();
        Course updated = new Course();
        CourseDTOGet dto = CourseDTOGet.builder().id(COURSE_ID).build();

        when(courseRepository.findById(COURSE_ID)).thenReturn(Optional.of(existing));
        when(courseMapper.fromPatchToEntity(patch, existing)).thenReturn(updated);
        when(courseRepository.save(updated)).thenReturn(updated);
        when(courseMapper.fromEntityToGet(updated)).thenReturn(dto);

        CourseDTOGet result = courseService.update(COURSE_ID, patch);

        assertThat(result).isEqualTo(dto);
    }

    @Test
    void deleteById_whenExists_deletes() {
        when(courseRepository.existsById(COURSE_ID)).thenReturn(true);

        courseService.deleteById(COURSE_ID);

        verify(courseRepository).deleteById(COURSE_ID);
    }

    @Test
    void getAll_returnsMappedList() {
        List<Course> entities = List.of(new Course());
        List<CourseDTOGet> dtos = List.of(CourseDTOGet.builder().id(COURSE_ID).build());

        when(courseRepository.findAll()).thenReturn(entities);
        when(courseMapper.fromEntityListToGetList(entities)).thenReturn(dtos);

        List<CourseDTOGet> result = courseService.getAll();

        assertThat(result).isEqualTo(dtos);
    }

    @Test
    void getEntityById_found_returnsEntity() {
        Course course = new Course();

        when(courseRepository.findById(COURSE_ID)).thenReturn(Optional.of(course));

        Course result = courseService.getEntityById(COURSE_ID);

        assertThat(result).isEqualTo(course);
    }

    @Test
    void getEntityById_notFound_throws() {
        when(courseRepository.findById(COURSE_ID)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> courseService.getEntityById(COURSE_ID))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void checkIfExists_found_returnsTrue() {
        when(courseRepository.existsById(COURSE_ID)).thenReturn(true);

        assertThat(courseService.checkIfExists(COURSE_ID)).isTrue();
    }

    @Test
    void checkIfExists_notFound_throws() {
        when(courseRepository.existsById(COURSE_ID)).thenReturn(false);

        assertThatThrownBy(() -> courseService.checkIfExists(COURSE_ID))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void getAllEntities_returnsList() {
        List<Course> list = List.of(new Course());
        when(courseRepository.findAll()).thenReturn(list);

        assertThat(courseService.getAllEntities()).isEqualTo(list);
    }

    @Test
    void getByIdForUser_returnsDTO() {
        Course course = new Course();
        CourseDTOGet dto = CourseDTOGet.builder().build();

        when(courseRepository.findById(COURSE_ID)).thenReturn(Optional.of(course));
        doNothing().when(scheduleService).checkUserAccessToSchedule(any(), eq(USER_ID));
        when(courseMapper.fromEntityToGet(course)).thenReturn(dto);

        CourseDTOGet result = courseService.getByIdForUser(USER_ID, COURSE_ID);

        assertThat(result).isEqualTo(dto);
    }

    @Test
    void getAllForUserSchedule_returnsList() {
        Schedule schedule = new Schedule();
        schedule.setId(SCHEDULE_ID);
        List<Course> courses = List.of(new Course());
        List<CourseDTOGet> dtos = List.of(CourseDTOGet.builder().build());

        when(scheduleService.getEntityByIdForUser(USER_ID, SCHEDULE_ID)).thenReturn(schedule);
        when(courseRepository.findAllByScheduleId(SCHEDULE_ID)).thenReturn(courses);
        when(courseMapper.fromEntityListToGetList(courses)).thenReturn(dtos);

        List<CourseDTOGet> result = courseService.getAllForUserSchedule(USER_ID, SCHEDULE_ID);

        assertThat(result).isEqualTo(dtos);
    }

    @Test
    void createForUserSchedule_createsCourse() {
        Schedule schedule = new Schedule();
        Course course = new Course();
        course.setSchedule(schedule);
        Course saved = new Course();
        CourseDTOPost post = CourseDTOPost.builder().name("CS").credits(5).build();
        CourseDTOGet dto = CourseDTOGet.builder().build();

        when(scheduleService.getEntityByIdForUser(USER_ID, SCHEDULE_ID)).thenReturn(schedule);
        when(courseMapper.fromPostToEntity(post)).thenReturn(course);
        when(courseRepository.save(course)).thenReturn(saved);
        when(courseMapper.fromEntityToGet(saved)).thenReturn(dto);

        CourseDTOGet result = courseService.createForUserSchedule(USER_ID, SCHEDULE_ID, post);

        assertThat(result).isEqualTo(dto);
    }

    @Test
    void updateForUser_updatesCourse() {
        Course course = new Course();
        Course patched = new Course();
        CourseDTOGet dto = CourseDTOGet.builder().build();
        CourseDTOPatch patch = CourseDTOPatch.builder().name("Bio").credits(2).build();

        when(courseRepository.findById(COURSE_ID)).thenReturn(Optional.of(course));
        doNothing().when(scheduleService).checkUserAccessToSchedule(any(), eq(USER_ID));
        when(courseMapper.fromPatchToEntity(patch, course)).thenReturn(patched);
        when(courseRepository.save(patched)).thenReturn(patched);
        when(courseMapper.fromEntityToGet(patched)).thenReturn(dto);

        CourseDTOGet result = courseService.updateForUser(USER_ID, COURSE_ID, patch);

        assertThat(result).isEqualTo(dto);
    }

    @Test
    void deleteByIdForUser_deletesCourse() {
        Course course = new Course();

        when(courseRepository.findById(COURSE_ID)).thenReturn(Optional.of(course));
        doNothing().when(scheduleService).checkUserAccessToSchedule(any(), eq(USER_ID));

        courseService.deleteByIdForUser(USER_ID, COURSE_ID);

        verify(courseRepository).delete(course);
    }

    @Test
    void getEntityByIdForUserSchedule_checksAccess() {
        Course course = new Course();
        Schedule schedule = new Schedule();
        course.setSchedule(schedule);

        when(courseRepository.findById(COURSE_ID)).thenReturn(Optional.of(course));
        doNothing().when(scheduleService).checkUserAccessToSchedule(schedule, USER_ID);

        Course result = courseService.getEntityByIdForUserSchedule(USER_ID, COURSE_ID);

        assertThat(result).isEqualTo(course);
    }

    @Test
    void getAllEntitiesForUserSchedule_returnsCourses() {
        Schedule schedule = new Schedule();
        schedule.setId(SCHEDULE_ID);
        List<Course> courses = List.of(new Course());

        when(scheduleService.getEntityByIdForUser(USER_ID, SCHEDULE_ID)).thenReturn(schedule);
        when(courseRepository.findAllByScheduleId(SCHEDULE_ID)).thenReturn(courses);

        List<Course> result = courseService.getAllEntitiesForUserSchedule(USER_ID, SCHEDULE_ID);

        assertThat(result).isEqualTo(courses);
    }
}
