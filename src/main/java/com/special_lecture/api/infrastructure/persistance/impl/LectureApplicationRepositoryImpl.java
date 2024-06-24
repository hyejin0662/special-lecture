package com.special_lecture.api.infrastructure.persistance.impl;

import java.util.Optional;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Repository;

import com.special_lecture.api.business.model.entity.LectureApplication;
import com.special_lecture.api.business.repo.LectureApplicationRepository;
import com.special_lecture.api.infrastructure.persistance.orm.LectureApplicationJpaRepository;
import com.special_lecture.common.exception.GlobalExceptionHandler;
import com.special_lecture.common.exception.LectureException;
import com.special_lecture.common.type.GlobalResponseCode;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class LectureApplicationRepositoryImpl implements LectureApplicationRepository {

    private final LectureApplicationJpaRepository lectureApplicationJpaRepository;

    @Override
    public Optional<LectureApplication> findByUserIdAndLectureId(Long userId, Long lectureId) {
        return lectureApplicationJpaRepository.findByUserIdAndLectureId(userId,lectureId);
    }

    @Override
    public int countByLectureId(Long lectureId) {
        return lectureApplicationJpaRepository.countByLectureId(lectureId);
    }

    @Override
    public void saveWithOptimisticLock(LectureApplication application) {
        try {
            lectureApplicationJpaRepository.save(application);
        } catch (OptimisticLockingFailureException e) {
            throw new LectureException(GlobalResponseCode.LOCKING_FAILURE);
        }
    }
}