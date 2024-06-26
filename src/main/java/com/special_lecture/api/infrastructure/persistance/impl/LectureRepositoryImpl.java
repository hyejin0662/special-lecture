package com.special_lecture.api.infrastructure.persistance.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.special_lecture.api.business.model.entity.Lecture;
import com.special_lecture.api.business.repo.LectureRepository;
import com.special_lecture.api.infrastructure.persistance.orm.LectureJpaRepository;
import com.special_lecture.common.exception.LectureException;
import com.special_lecture.common.type.GlobalResponseCode;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class LectureRepositoryImpl implements LectureRepository {

	private final LectureJpaRepository lectureJpaRepository;

	@Override
	public List<Lecture> findAll() {
		return lectureJpaRepository.findAll();
	}

	@Override
	public Lecture findById(Long lectureId) {
		return lectureJpaRepository.findById(lectureId).orElseThrow(()->new LectureException(GlobalResponseCode.NOT_FOUND_LECTURE));
	}

	@Override
	public Lecture findByIdWithLock(Long lectureId) {
		return lectureJpaRepository.findByIdWithLock(lectureId).orElseThrow(() -> new LectureException(GlobalResponseCode.NOT_FOUND_LECTURE));
	}

}