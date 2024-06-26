package com.special_lecture.api.application.dto.request;

import com.special_lecture.api.business.model.dto.LectureApplicationCommand;
import com.special_lecture.api.business.model.dto.LectureCommand;
import com.special_lecture.api.business.model.entity.LectureApplication;
import com.special_lecture.common.mapper.DtoConverter;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class LectureApplicationRequest {

    @NotNull(message = "User ID is required.")
    private Long userId;

    @NotNull(message = "Lecture ID is required.")
    private Long lectureId;

    public LectureApplicationCommand toCommand() {
        return DtoConverter.convert(this, LectureApplicationCommand.class);
    }
}