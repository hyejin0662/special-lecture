package com.special_lecture.api.business.repo;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.special_lecture.api.business.model.entity.Lecture;
import com.special_lecture.api.business.model.entity.LectureApplication;

public interface LectureRepository {

	List<Lecture> findAll();

	Lecture findById(Long lectureId);

	Lecture findByIdWithLock(Long lectureId);
}
