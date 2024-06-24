package com.special_lecture.api.business.repo;

import java.util.Optional;

import com.special_lecture.api.business.model.entity.LectureApplication;

public interface LectureApplicationRepository {
	Optional<LectureApplication> findByUserIdAndLectureId(Long userId, Long lectureId);

	int countByLectureId(Long lectureId);

	void saveWithOptimisticLock(LectureApplication application);
}
