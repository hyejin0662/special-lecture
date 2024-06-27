package com.special_lecture.api.application.dto.request;

import com.special_lecture.api.business.model.dto.LectureApplicationCommand;
import com.special_lecture.common.mapper.DtoConverter;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LectureApplicationRequest {

    @NotNull(message = "User ID는 필수입니다.")
    private String userId;

    @NotNull(message = "Lecture ID 는 필수입니다.")
    private Long lectureId;


    public LectureApplicationCommand toCommand() {
        return DtoConverter.convert(this, LectureApplicationCommand.class);
    }
}