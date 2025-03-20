package com.example.schedule_composer.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Priority levels for a course")
public enum CoursePriority {
    @Schema(description = "High priority course")
    HIGH,
    @Schema(description = "Medium priority course")
    MEDIUM,
    @Schema(description = "Low priority course")
    LOW;

    @JsonCreator
    public static CoursePriority fromString(String value) {
        for (CoursePriority priority : values()) {
            if (priority.name().equalsIgnoreCase(value)) {
                return priority;
            }
        }
        throw new IllegalArgumentException("Invalid course priority: " + value);
    }

    @JsonValue
    public String toValue() {
        return this.name();

    }

}
