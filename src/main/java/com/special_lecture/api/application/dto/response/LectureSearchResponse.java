package com.special_lecture.api.application.dto.response;

import com.special_lecture.common.type.LectureStatus;
import java.time.LocalDateTime;
import java.util.List;

import com.special_lecture.api.business.model.dto.LectureInfo;
import com.special_lecture.common.mapper.DtoConverter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LectureSearchResponse {

	private Long lectureId;
	private String topic;
	private String description;
	private String instructor;
	private LectureStatus lectureStatus;
	private LocalDateTime createAt;
	private LocalDateTime updateAt;
	private int capacity;

	public static LectureSearchResponse from(LectureInfo info) {

		return DtoConverter.convert(info,LectureSearchResponse.class);
	}
}
