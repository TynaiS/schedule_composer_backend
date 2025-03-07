package com.example.schedule_composer.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Teaching modes")
public enum TeachingMode {
    ONLINE, CLASSROOM;

    @JsonCreator
    public static TeachingMode fromString(String value) {
        for (TeachingMode mode : values()) {
            if (mode.name().equalsIgnoreCase(value)) {
                return mode;
            }
        }
        throw new IllegalArgumentException("Invalid teaching mode: " + value);
    }

    @JsonValue
    public String toValue() {
        return this.name();

    }
}
