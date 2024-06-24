package com.special_lecture.api.business.model.dto;

import com.special_lecture.api.business.model.entity.Lecture;
import com.special_lecture.common.mapper.DtoConverter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LectureCommand {
    private Long userId;
    private Long lectureId;

    public Lecture toEntity() {
        return DtoConverter.convert(this, Lecture.class);
    }
}