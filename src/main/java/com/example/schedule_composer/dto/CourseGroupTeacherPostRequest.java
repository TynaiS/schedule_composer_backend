package com.example.schedule_composer.dto;

import com.example.schedule_composer.utils.CourseType;
import lombok.Data;

@Data
public class CourseGroupTeacherPostRequest {
    private Long groupId;
    private Long courseId;
    private Long teacherId;
    private Integer hoursAWeek;
    private Integer hoursTotal;
    private CourseType type;

    public Long getGroupId() {
        return groupId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public Long getTeacherId() {
        return teacherId;
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

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
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