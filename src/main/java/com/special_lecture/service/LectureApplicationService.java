package com.special_lecture.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.special_lecture.domain.dto.request.LectureApplicationRequest;
import com.special_lecture.domain.dto.response.LectureApplicationResponse;
import com.special_lecture.domain.entity.Lecture;
import com.special_lecture.domain.entity.LectureApplication;
import com.special_lecture.domain.type.GlobalResponseCode;
import com.special_lecture.exception.LectureException;
import com.special_lecture.repository.LectureApplicationRepository;
import com.special_lecture.repository.LectureRepository;
import com.special_lecture.repository.impl.LectureApplicationRepositoryImpl;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class LectureApplicationService {

    private final LectureRepository lectureRepository;
    private final LectureApplicationRepository lectureApplicationRepository;
    private final LectureApplicationRepositoryImpl lectureApplicationRepositoryImpl;

    @Transactional
    public LectureApplicationResponse applyForLecture(LectureApplicationRequest request) {
        Lecture lecture = lectureRepository.findById(request.getLectureId())
            .orElseThrow(() ->  new LectureException(GlobalResponseCode.NOT_FOUND_LECTURE));

        if (lecture.isFull(lectureApplicationRepository.countByLectureId(request.getLectureId()))) {
            throw new LectureException(GlobalResponseCode.LECTURE_FULL);
        }

        Optional<LectureApplication> existingApplication = lectureApplicationRepository
            .findByUserIdAndLectureId(request.getUserId(), request.getLectureId());
        if (existingApplication.isPresent()) {
            return new LectureApplicationResponse(false);
        }

        LectureApplication application = new LectureApplication(request.getUserId(), lecture);
        lectureApplicationRepositoryImpl.saveWithPessimisticLock(application);

        return new LectureApplicationResponse(true);
    }

    public LectureApplicationResponse checkApplicationStatus(Long userId, Long lectureId) {
        Optional<LectureApplication> existingApplication = lectureApplicationRepository.findByUserIdAndLectureId(userId, lectureId);
        return new LectureApplicationResponse(existingApplication.isPresent());
    }
}