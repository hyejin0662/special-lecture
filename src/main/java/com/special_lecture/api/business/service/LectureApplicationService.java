package com.special_lecture.api.business.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.special_lecture.api.application.dto.response.LectureApplicationResponse;
import com.special_lecture.api.business.model.dto.LectureCommand;
import com.special_lecture.api.business.model.entity.Lecture;
import com.special_lecture.api.business.model.entity.LectureApplication;
import com.special_lecture.api.business.repo.LectureApplicationRepository;
import com.special_lecture.api.infrastructure.persistance.orm.LectureApplicationJpaRepository;
import com.special_lecture.api.business.vaildatior.LectureValidator;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class LectureApplicationService {

    private final LectureValidator lectureValidator;
    private final LectureApplicationRepository lectureApplicationRepository;

    @Transactional
    public LectureApplicationResponse applyForLecture(LectureCommand command) {
        Lecture lecture = lectureValidator.validateLectureExists(command.getLectureId());
        lectureValidator.validateLectureCapacity(lecture);
        lectureValidator.validateUserNotAlreadyApplied(command.getUserId(), command.getLectureId());

        LectureApplication application = new LectureApplication(command.getUserId(), lecture);
        lectureApplicationRepository.saveWithOptimisticLock(application);

        return new LectureApplicationResponse(true, lecture.getVersion());
    }

    public LectureApplicationResponse checkApplicationStatus(Long userId, Long lectureId) {
        Optional<LectureApplication> existingApplication = lectureApplicationRepository.findByUserIdAndLectureId(userId, lectureId);
        return new LectureApplicationResponse(existingApplication.isPresent(), existingApplication.map(app -> app.getLecture().getVersion()).orElse(null));
    }


}