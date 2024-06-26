package com.special_lecture.api.application.facade;

import com.special_lecture.api.application.dto.request.LectureApplicationRequest;
import com.special_lecture.api.application.dto.response.LectureApplicationResponse;
import com.special_lecture.api.business.model.dto.LectureApplicationCommand;
import com.special_lecture.api.business.model.dto.LectureApplicationInfo;
import com.special_lecture.api.business.model.dto.LectureCommand;
import com.special_lecture.api.business.model.dto.LectureInfo;
import com.special_lecture.api.business.model.entity.Lecture;
import com.special_lecture.api.business.model.entity.LectureApplication;
import com.special_lecture.api.business.repo.LectureApplicationRepository;
import com.special_lecture.api.business.repo.LectureRepository;
import com.special_lecture.api.business.service.LectureApplicationService;
import com.special_lecture.common.exception.LectureException;
import com.special_lecture.common.type.GlobalResponseCode;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class LectureFacade {

  private final LectureRepository lectureRepository;
  private final LectureApplicationRepository lectureApplicationRepository;

  private final LectureApplicationService lectureApplicationService;

//  public LectureApplicationResponse applyForLecture(LectureApplicationRequest request) {
//    LectureApplicationCommand command = request.toCommand();
//    LectureApplicationInfo info = lectureApplicationService.applyForLecture(command);
//    return LectureApplicationResponse.from(info);
//  }
//
//  public LectureApplicationResponse checkApplicationStatus(Long userId, Long lectureId) {
//    LectureApplicationInfo info = lectureApplicationService.checkApplicationStatus(userId, lectureId);
//    return LectureApplicationResponse.from(info);
//  }



}
