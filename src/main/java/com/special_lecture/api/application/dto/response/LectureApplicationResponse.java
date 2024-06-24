package com.special_lecture.api.application.dto.response;

import com.special_lecture.api.business.model.dto.LectureInfo;
import com.special_lecture.common.mapper.DtoConverter;

import lombok.Data;

@Data
public class LectureApplicationResponse {
    private final boolean success;
    private final Long version;

    // public static LectureApplicationResponse from(LectureInfo info) {
    //     return DtoConverter.convert(info, LectureApplicationResponse.class);
    // }

    public static LectureApplicationResponse from(LectureInfo info) {
        return new LectureApplicationResponse(true, info.getVersion());
    }
}