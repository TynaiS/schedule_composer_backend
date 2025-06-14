package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.get.CourseDTOGet;
import com.example.schedule_composer.dto.patch.CourseDTOPatch;
import com.example.schedule_composer.dto.post.CourseDTOPost;
import com.example.schedule_composer.entity.User;
import com.example.schedule_composer.security.JwtAuthenticationFilter;
import com.example.schedule_composer.security.JwtService;
import com.example.schedule_composer.service.CourseService;
import com.example.schedule_composer.utils.ApiConstants;
import com.example.schedule_composer.utils.types.CoursePriority;
import com.example.schedule_composer.utils.types.TeachingMode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CourseController.class)
@AutoConfigureMockMvc(addFilters = false)
class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private ObjectMapper objectMapper;

    private User mockUser;

    @BeforeEach
    void setUp() {
        mockUser = new User();
        mockUser.setId(1L);
        mockUser.setName("mockUser");

        var auth = new UsernamePasswordAuthenticationToken(
                mockUser,
                null,
                List.of(new SimpleGrantedAuthority("ROLE_CREATOR"))
        );

        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @AfterEach
    void tearDown() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void testGetById() throws Exception {
        Long courseId = 42L;
        CourseDTOGet dto = CourseDTOGet.builder()
                .id(courseId)
                .name("Course A")
                .build();

        when(courseService.getByIdForUser(eq(mockUser.getId()), eq(courseId))).thenReturn(dto);

        mockMvc.perform(get(ApiConstants.COURSE_API + "/" + courseId))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(dto)));
    }

    @Test
    void testGetAllForSchedule() throws Exception {
        Long scheduleId = 5L;

        List<CourseDTOGet> list = List.of(
                CourseDTOGet.builder().id(1L).name("C1").build(),
                CourseDTOGet.builder().id(2L).name("C2").build()
        );

        when(courseService.getAllForUserSchedule(eq(mockUser.getId()), eq(scheduleId))).thenReturn(list);

        mockMvc.perform(get(ApiConstants.COURSE_API + "/for-schedule/" + scheduleId))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(list)));
    }

    @Test
    void testCreateCourse() throws Exception {
        Long scheduleId = 7L;

        CourseDTOPost postRequest = CourseDTOPost.builder()
                .name("New Course")
                .credits(1)
                .build();

        CourseDTOGet createdDto = CourseDTOGet.builder()
                .id(99L)
                .name(postRequest.getName())
                .credits(postRequest.getCredits())
                .build();

        when(courseService.createForUserSchedule(eq(mockUser.getId()), eq(scheduleId), any(CourseDTOPost.class)))
                .thenReturn(createdDto);

        mockMvc.perform(post(ApiConstants.COURSE_API + "/for-schedule/" + scheduleId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postRequest)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(createdDto)));
    }

    @Test
    void testUpdateCourse() throws Exception {
        Long courseId = 11L;

        CourseDTOPatch patchRequest = CourseDTOPatch.builder()
                .name("Updated Name")
                .build();

        CourseDTOGet updatedDto = CourseDTOGet.builder()
                .id(courseId)
                .name(patchRequest.getName())
                .build();

        when(courseService.updateForUser(eq(mockUser.getId()), eq(courseId), any(CourseDTOPatch.class)))
                .thenReturn(updatedDto);

        mockMvc.perform(patch(ApiConstants.COURSE_API + "/" + courseId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patchRequest)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(updatedDto)));
    }

    @Test
    void testDeleteCourse() throws Exception {
        Long courseId = 20L;

        Mockito.doNothing().when(courseService).deleteByIdForUser(mockUser.getId(), courseId);

        mockMvc.perform(delete(ApiConstants.COURSE_API + "/" + courseId))
                .andExpect(status().isNoContent());

        verify(courseService).deleteByIdForUser(mockUser.getId(), courseId);
    }

    @Test
    void testGetCoursePriorities() throws Exception {
        List<CoursePriority> priorities = List.of(CoursePriority.values());

        mockMvc.perform(get(ApiConstants.COURSE_API + "/course-priorities"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(priorities)));
    }

    @Test
    void testGetTeachingModes() throws Exception {
        List<TeachingMode> modes = List.of(TeachingMode.values());

        mockMvc.perform(get(ApiConstants.COURSE_API + "/teaching-modes"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(modes)));
    }
}
