package com.special_lecture.api.infrastructure.persistance.impl;

import org.springframework.stereotype.Repository;

import com.special_lecture.api.business.repo.LectureRepository;
import com.special_lecture.api.infrastructure.persistance.orm.LectureJpaRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class LectureRepositoryImpl implements LectureRepository {

	private final LectureJpaRepository lectureJpaRepository;

}