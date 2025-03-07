package com.example.schedule_composer.repository;

import com.example.schedule_composer.entity.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)  // Use real DB if needed
public class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void testSaveAndFindById() {
        Course course = new Course();
        course.setName("Spring Boot");
        courseRepository.save(course);

        Optional<Course> found = courseRepository.findById(course.getId());

        assertTrue(found.isPresent());
    }
}
