package com.special_lecture.api.business.service.impl;

import com.special_lecture.api.business.model.dto.LectureApplicationCommand;

import com.special_lecture.api.business.service.LectureApplicationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.special_lecture.api.business.model.dto.LectureApplicationApplyInfo;
import com.special_lecture.api.business.model.dto.LectureApplicationStatusInfo;
import com.special_lecture.api.business.model.entity.Lecture;
import com.special_lecture.api.business.model.entity.LectureApplication;
import com.special_lecture.api.business.repo.LectureApplicationRepository;
import com.special_lecture.api.business.repo.LectureRepository;
import com.special_lecture.api.business.vaildatior.LectureValidator;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class LectureApplicationServiceImpl implements LectureApplicationService {

    private final LectureValidator lectureValidator;
    private final LectureApplicationRepository lectureApplicationRepository;
    private final LectureRepository lectureRepository;


    @Override
    @Transactional
    public LectureApplicationApplyInfo applyForLecture(LectureApplicationCommand command) {

        Lecture lecture = lectureRepository.findByIdWithLock(command.getLectureId());

        lectureValidator.validateLectureCapacity(lecture);
        lectureValidator.validateUserNotAlreadyApplied(command.getUserId(), command.getLectureId());

        LectureApplication application = new LectureApplication(command.getUserId(), lecture);
        lectureApplicationRepository.save(application);

        return LectureApplicationApplyInfo.from(application);
    }

    @Override
    @Transactional
    public LectureApplicationStatusInfo checkApplicationStatus(String userId, Long lectureId) {
        boolean statusResult = lectureApplicationRepository.existsByUserIdAndLectureId(userId, lectureId);
        return new LectureApplicationStatusInfo(userId,statusResult);
    }


}