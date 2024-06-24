package com.special_lecture.api.application.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class LectureBaseDto {
    private final Long lectureId;
    private final Long version;
    private final String topic;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final int capacity;
    private final int registeredCount;
}