package com.example.schedule_composer.entity;

import com.example.schedule_composer.utils.CourseType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseGroupTeacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id", nullable = false)
    private Group group;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id", nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id", nullable = false)
    private Teacher teacher;


    private Integer hoursAWeek;
    private Integer hoursTotal;
    private CourseType type;

    //  Temporarily, Lombok's @Data doesn't work


    public Long getId() {
        return id;
    }

    public Group getGroup() {
        return group;
    }

    public Course getCourse() {
        return course;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Integer getHoursAWeek() {
        return hoursAWeek;
    }

    public Integer getHoursTotal() {
        return hoursTotal;
    }

    public CourseType getType() {
        return type;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void setHoursAWeek(Integer hoursAWeek) {
        this.hoursAWeek = hoursAWeek;
    }

    public void setHoursTotal(Integer hoursTotal) {
        this.hoursTotal = hoursTotal;
    }

    public void setType(CourseType type) {
        this.type = type;
    }
}
