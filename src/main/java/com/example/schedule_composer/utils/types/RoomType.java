package com.example.schedule_composer.utils.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Room types")
public enum RoomType {
    CLASSROOM, LAB;

    @JsonCreator
    public static RoomType fromString(String value) {
        for (RoomType type : values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid room type: " + value);
    }

    @JsonValue
    public String toValue() {
        return this.name();

    }
}
