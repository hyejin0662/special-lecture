package com.special_lecture.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.special_lecture.domain.model.WebResponseData;
import com.special_lecture.domain.type.GlobalResponseCode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(LectureException.class)
	public WebResponseData<Object> lectureExceptionHandler(LectureException e) {
		log.error(e.getGlobalResponseCode() + "에러가 발생했습니다.");
		return WebResponseData.error(e.getGlobalResponseCode());
	}

	@ExceptionHandler(Exception.class)
	public WebResponseData<Object> globalExceptionHandler(Exception e) {
		log.error("서버 내부적 오류가 발생했습니다. -> "+e);
		return WebResponseData.error(GlobalResponseCode.SERVER_INTERVAL_ERROR);
	}
}
