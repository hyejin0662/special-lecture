package com.special_lecture.domain.dto.request;

import lombok.Data;

@Data
public class LectureApplicationRequest {
    private final Long userId;
    private final Long lectureId;
}