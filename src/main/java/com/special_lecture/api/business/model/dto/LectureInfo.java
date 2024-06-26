package com.special_lecture.api.business.model.dto;

import java.time.LocalDateTime;

import com.special_lecture.api.business.model.entity.Lecture;
import com.special_lecture.common.mapper.DtoConverter;
import com.special_lecture.common.type.LectureStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LectureInfo {
    private Long lectureId;
    private String topic;
    private String description;
    private String instructor;
    private LectureStatus lectureStatus;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private int capacity;

    public static LectureInfo from(Lecture lecture) {
        return DtoConverter.convert(lecture, LectureInfo.class);
    }
}