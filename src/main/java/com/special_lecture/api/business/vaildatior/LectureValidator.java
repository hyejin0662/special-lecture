package com.special_lecture.api.business.vaildatior;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.special_lecture.api.business.model.entity.Lecture;
import com.special_lecture.api.business.model.entity.LectureApplication;
import com.special_lecture.api.infrastructure.persistance.orm.LectureApplicationJpaRepository;
import com.special_lecture.api.infrastructure.persistance.orm.LectureJpaRepository;
import com.special_lecture.common.exception.LectureException;
import com.special_lecture.common.type.GlobalResponseCode;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LectureValidator {

    private final LectureJpaRepository lectureJpaRepository;
    private final LectureApplicationJpaRepository lectureApplicationJpaRepository;

    public Lecture validateLectureExists(Long lectureId) {
        return lectureJpaRepository.findById(lectureId)
                .orElseThrow(() -> new LectureException(GlobalResponseCode.NOT_FOUND_LECTURE));
    }

    public void validateLectureCapacity(Lecture lecture) {
        if (lecture.isFull((int) lectureApplicationJpaRepository.countByLectureId(lecture.getLectureId()))) {
            throw new LectureException(GlobalResponseCode.LECTURE_FULL);
        }
    }

    public void validateUserNotAlreadyApplied(Long userId, Long lectureId) {
        Optional<LectureApplication> existingApplication = lectureApplicationJpaRepository
                .findByUserIdAndLectureId(userId, lectureId);
        if (existingApplication.isPresent()) {
            throw new LectureException(GlobalResponseCode.ALREADY_APPLIED);
        }
    }
}