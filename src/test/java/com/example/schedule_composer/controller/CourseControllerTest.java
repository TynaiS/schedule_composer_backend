//package com.example.schedule_composer.controller;
//
//import com.example.schedule_composer.dto.get.CourseDTOGet;
//import com.example.schedule_composer.dto.patch.CourseDTOPatch;
//import com.example.schedule_composer.dto.post.CourseDTOPost;
//import com.example.schedule_composer.entity.User;
//import com.example.schedule_composer.service.CourseService;
//import com.example.schedule_composer.utils.types.CoursePriority;
//import com.example.schedule_composer.utils.types.TeachingMode;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.*;
//
//class CourseControllerTest {
//
//    @Mock
//    private CourseService courseService;
//
//    @InjectMocks
//    private CourseController courseController;
//
//    private User mockUser;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        mockUser = new User();
//        mockUser.setId(42L);
//    }
//
//    @Test
//    void testGetById() {
//        Long courseId = 10L;
//        CourseDTOGet mockCourse = CourseDTOGet.builder()
//                .id(courseId)
//                .name("Sample Course")
//                .build();
//
//        when(courseService.getByIdForUser(mockUser.getId(), courseId)).thenReturn(mockCourse);
//
//        ResponseEntity<CourseDTOGet> response = courseController.getById(mockUser, courseId);
//
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(response.getBody()).isEqualTo(mockCourse);
//        verify(courseService).getByIdForUser(mockUser.getId(), courseId);
//    }
//
//    @Test
//    void testGetAll() {
//        Long scheduleId = 5L;
//        List<CourseDTOGet> mockCourses = List.of(
//                CourseDTOGet.builder().id(1L).name("Math 101").build(),
//                CourseDTOGet.builder().id(2L).name("Physics 202").build()
//        );
//        when(courseService.getAllForUserSchedule(mockUser.getId(), scheduleId)).thenReturn(mockCourses);
//
//        ResponseEntity<List<CourseDTOGet>> response = courseController.getAll(mockUser, scheduleId);
//
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(response.getBody()).isEqualTo(mockCourses);
//        verify(courseService).getAllForUserSchedule(mockUser.getId(), scheduleId);
//    }
//
//    @Test
//    void testCreate() {
//        Long scheduleId = 7L;
//        CourseDTOPost postRequest = new CourseDTOPost("Course name", 2);
//        // You can set fields on postRequest if needed
//
//        CourseDTOGet createdCourse = CourseDTOGet.builder()
//                .id(100L)
//                .name("New Course")
//                .build();
//
//        when(courseService.createForUserSchedule(mockUser.getId(), scheduleId, postRequest)).thenReturn(createdCourse);
//
//        ResponseEntity<CourseDTOGet> response = courseController.create(mockUser, scheduleId, postRequest);
//
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
//        assertThat(response.getBody()).isEqualTo(createdCourse);
//        verify(courseService).createForUserSchedule(mockUser.getId(), scheduleId, postRequest);
//    }
//
//    @Test
//    void testUpdate() {
//        Long courseId = 8L;
//        CourseDTOPatch patchRequest = new CourseDTOPatch();
//        // You can set fields on patchRequest if needed
//
//        CourseDTOGet updatedCourse = CourseDTOGet.builder()
//                .id(courseId)
//                .name("Updated Course")
//                .build();
//
//        when(courseService.updateForUser(mockUser.getId(), courseId, patchRequest)).thenReturn(updatedCourse);
//
//        ResponseEntity<CourseDTOGet> response = courseController.update(mockUser, courseId, patchRequest);
//
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(response.getBody()).isEqualTo(updatedCourse);
//        verify(courseService).updateForUser(mockUser.getId(), courseId, patchRequest);
//    }
//
//    @Test
//    void testDeleteById() {
//        Long courseId = 9L;
//
//        doNothing().when(courseService).deleteByIdForUser(mockUser.getId(), courseId);
//
//        ResponseEntity<Void> response = courseController.deleteById(mockUser, courseId);
//
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
//        assertThat(response.getBody()).isNull();
//        verify(courseService).deleteByIdForUser(mockUser.getId(), courseId);
//    }
//
//    @Test
//    void testGetCoursePriorities() {
//        ResponseEntity<List<CoursePriority>> response = courseController.getCoursePriorities();
//
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(response.getBody()).containsExactly(CoursePriority.values());
//    }
//
//    @Test
//    void testGetTeachingModes() {
//        ResponseEntity<List<TeachingMode>> response = courseController.getTeachingModes();
//
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(response.getBody()).containsExactly(TeachingMode.values());
//    }
//}
