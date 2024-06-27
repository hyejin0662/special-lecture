package com.special_lecture.api.business.repo;

import com.special_lecture.api.business.model.entity.Lecture;
import com.special_lecture.api.business.model.entity.LectureApplication;

public interface LectureApplicationRepository {

	void save(LectureApplication application);


	boolean existsByUserIdAndLectureId(String userId, Long lectureId);


}
