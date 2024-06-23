package com.special_lecture.domain.type;

public enum LectureStatus {
	COMPLETED,
	PENDING, // 30안에 못들었지만, 취소건이 생기면 연락할 대기중인 예비 수강생
	CANCELLED
}