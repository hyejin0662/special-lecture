package com.special_lecture.api.application.dto.response;

import com.special_lecture.api.business.model.dto.LectureApplicationApplyInfo;
import com.special_lecture.api.business.model.dto.LectureApplicationStatusInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LectureApplicationStatusResponse {
	
	private String userId;
	private boolean success;

	public static LectureApplicationStatusResponse from(LectureApplicationStatusInfo info) {
		return LectureApplicationStatusResponse.builder()
			.userId(info.getUserId())
			.success(info.isSuccess())
			.build();
	}
}
