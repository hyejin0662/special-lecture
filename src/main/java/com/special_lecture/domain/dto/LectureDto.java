package com.special_lecture.domain.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class LectureDto {
    private final Long lectureId;
    private final String topic;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final int capacity;
    private final int registeredCount;
}