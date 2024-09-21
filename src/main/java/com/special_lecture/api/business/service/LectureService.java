package com.special_lecture.api.business.service;

import java.util.List;

import com.special_lecture.api.business.model.dto.LectureInfo;

public interface LectureService {
	List<LectureInfo> getAllLectures();
}
