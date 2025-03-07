package com.example.schedule_composer.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Course types")
public enum CourseType {
    OBLIGATORY, ELECTIVE;

    @JsonCreator
    public static CourseType fromString(String value) {
        for (CourseType type : values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid course type: " + value);
    }

    @JsonValue
    public String toValue() {
        return this.name();

    }
}
