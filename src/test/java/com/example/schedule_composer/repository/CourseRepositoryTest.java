package com.example.schedule_composer.repository;

import com.example.schedule_composer.entity.Course;
import com.example.schedule_composer.entity.Schedule;
import com.example.schedule_composer.entity.User;
import com.example.schedule_composer.utils.types.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(properties = "spring.sql.init.mode=never")
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private UserRepository userRepository;

    private User createTestUser() {
        return userRepository.save(User.builder()
                .name("  Test User  ")
                .email("test@example.com")
                .password("password")
                .createdAt(LocalDateTime.now())
                .role(UserRole.CREATOR)
                .build());
    }

    private Schedule createTestSchedule(User owner, String scheduleName) {
        return scheduleRepository.save(Schedule.builder()
                .name(scheduleName)
                .owner(owner)
                .build());
    }

    private Course createTestCourse(Schedule schedule, String name) {
        return courseRepository.save(Course.builder()
                .name(name)
                .credits(3)
                .schedule(schedule)
                .build());
    }

    @Test
    void testSaveAndTrimCourseName() {
        User user = createTestUser();
        Schedule schedule = createTestSchedule(user, "Schedule 1");

        Course course = createTestCourse(schedule, "  Algorithms  ");
        assertThat(course.getId()).isNotNull();
        assertThat(course.getName()).isEqualTo("Algorithms");
    }

    @Test
    void testFindAll() {
        User user = createTestUser();
        Schedule schedule = createTestSchedule(user, "Schedule 2");
        createTestCourse(schedule, "Math");
        createTestCourse(schedule, "Physics");

        List<Course> allCourses = courseRepository.findAll();
        assertThat(allCourses).hasSize(2);
    }

    @Test
    void testFindAllByScheduleId() {
        User user = createTestUser();
        Schedule schedule1 = createTestSchedule(user,"Schedule 3");
        Schedule schedule2 = createTestSchedule(user, "Schedule 4");

        createTestCourse(schedule1, "Course A");
        createTestCourse(schedule1, "Course B");
        createTestCourse(schedule2, "Course C");

        List<Course> coursesSchedule1 = courseRepository.findAllByScheduleId(schedule1.getId());
        List<Course> coursesSchedule2 = courseRepository.findAllByScheduleId(schedule2.getId());

        assertThat(coursesSchedule1).hasSize(2)
                .extracting(Course::getName)
                .containsExactlyInAnyOrder("Course A", "Course B");

        assertThat(coursesSchedule2).hasSize(1)
                .extracting(Course::getName)
                .containsExactly("Course C");
    }

    @Test
    void testFindById() {
        User user = createTestUser();
        Schedule schedule = createTestSchedule(user, "Schedule 4");
        Course savedCourse = createTestCourse(schedule, "Databases");

        Optional<Course> result = courseRepository.findById(savedCourse.getId());
        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo("Databases");
    }

    @Test
    void testUpdateCourse() {
        User user = createTestUser();
        Schedule schedule = createTestSchedule(user, "Schedule 5");
        Course course = createTestCourse(schedule, "Old Name");

        course.setName("  New Name  ");
        course = courseRepository.save(course);

        Optional<Course> updated = courseRepository.findById(course.getId());
        assertThat(updated).isPresent();
        assertThat(updated.get().getName()).isEqualTo("New Name");
    }

    @Test
    void testDeleteCourse() {
        User user = createTestUser();
        Schedule schedule = createTestSchedule(user,"Schedule 6");
        Course course = createTestCourse(schedule, "To Delete");

        courseRepository.delete(course);

        Optional<Course> result = courseRepository.findById(course.getId());
        assertThat(result).isEmpty();
    }
}
