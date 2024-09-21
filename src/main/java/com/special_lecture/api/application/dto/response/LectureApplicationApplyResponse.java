package com.special_lecture.api.application.dto.response;

import java.time.LocalDateTime;

import com.special_lecture.api.business.model.dto.LectureApplicationApplyInfo;
import com.special_lecture.api.business.model.dto.LectureInfo;
import com.special_lecture.common.mapper.DtoConverter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LectureApplicationApplyResponse {
    private Long applicationId;
    private String userId;
    private Long lectureId;
    private LocalDateTime applicationDate;



    public static LectureApplicationApplyResponse from(LectureApplicationApplyInfo info) {
        return DtoConverter.convert(info, LectureApplicationApplyResponse.class);
    }


}