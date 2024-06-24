package com.special_lecture.common.type;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


// TODO : global responseCode로 변경
@RequiredArgsConstructor
@Getter
public enum GlobalResponseCode {
	SUCCESS_CODE("성공적으로 작업을 수행 했습니다.", 1000, HttpStatus.OK),

	SERVER_INTERVAL_ERROR("서버 내부적 오류입니다.",1002,HttpStatus.BAD_REQUEST),
	LECTURE_FULL("수용 인원이 초과되었습니다.",1003,HttpStatus.BAD_REQUEST),
	NOT_FOUND_USER("유저를 찾을 수 없습니다.",1004,HttpStatus.BAD_REQUEST),
	NOT_FOUND_LECTURE("해당 강의를 찾을 수 없습니다.",1005,HttpStatus.BAD_REQUEST),
	ALREADY_APPLIED("이미 해당 강의에 신청하셨습니다.",1008,HttpStatus.BAD_REQUEST),
	LOCKING_FAILURE("Optimistic locking failure.",1009,HttpStatus.BAD_REQUEST);
	;




	private final String description;
	private final int CustomCode;
	private final HttpStatus httpStatus;
}
