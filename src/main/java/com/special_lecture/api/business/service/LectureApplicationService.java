package com.special_lecture.api.business.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.special_lecture.api.business.model.dto.LectureApplicationApplyInfo;
import com.special_lecture.api.business.model.dto.LectureApplicationCommand;
import com.special_lecture.api.business.model.dto.LectureApplicationStatusInfo;

public interface LectureApplicationService {

	@Transactional
	LectureApplicationApplyInfo applyForLecture(LectureApplicationCommand command);

	@Transactional
	LectureApplicationStatusInfo checkApplicationStatus(String userId, Long lectureId);
}
