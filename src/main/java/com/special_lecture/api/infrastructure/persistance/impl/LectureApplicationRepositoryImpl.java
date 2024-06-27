package com.special_lecture.api.infrastructure.persistance.impl;

import org.springframework.stereotype.Repository;

import com.special_lecture.api.business.model.entity.LectureApplication;
import com.special_lecture.api.business.repo.LectureApplicationRepository;
import com.special_lecture.api.infrastructure.persistance.orm.LectureApplicationJpaRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class LectureApplicationRepositoryImpl implements LectureApplicationRepository {

    private final LectureApplicationJpaRepository lectureApplicationJpaRepository;



    @Override
    public void save(LectureApplication application) {
            lectureApplicationJpaRepository.save(application);
    }

    @Override
    public boolean existsByUserIdAndLectureId(String userId, Long lectureId) {
        return lectureApplicationJpaRepository.existsByUserIdAndLectureLectureId(userId,lectureId);
    }
}