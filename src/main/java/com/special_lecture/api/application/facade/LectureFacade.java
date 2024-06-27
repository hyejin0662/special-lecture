package com.special_lecture.api.application.facade;

import java.util.List;
import java.util.stream.Collectors;

import com.special_lecture.api.application.dto.request.LectureApplicationRequest;
import com.special_lecture.api.application.dto.response.LectureApplicationApplyResponse;
import com.special_lecture.api.application.dto.response.LectureApplicationStatusResponse;
import com.special_lecture.api.application.dto.response.LectureSearchResponse;
import com.special_lecture.api.business.model.dto.LectureApplicationApplyInfo;
import com.special_lecture.api.business.model.dto.LectureApplicationStatusInfo;
import com.special_lecture.api.business.model.dto.LectureInfo;
import com.special_lecture.api.business.service.impl.LectureApplicationServiceImpl;
import com.special_lecture.api.business.service.impl.LectureServiceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LectureFacade {


  private final LectureApplicationServiceImpl lectureApplicationServiceImpl;
  private final LectureServiceImpl lectureServiceImpl;

 public LectureApplicationApplyResponse applyForLecture(LectureApplicationRequest request) {
     LectureApplicationApplyInfo lectureApplicationApplyInfo = lectureApplicationServiceImpl.applyForLecture(request.toCommand());
   return LectureApplicationApplyResponse.from(lectureApplicationApplyInfo);
 }

 public LectureApplicationStatusResponse checkApplicationStatus(String userId, Long lectureId) {
   LectureApplicationStatusInfo info = lectureApplicationServiceImpl.checkApplicationStatus(userId, lectureId);
   return LectureApplicationStatusResponse.from(info);
 }

    public List<LectureSearchResponse> getAllLectures() {
        List<LectureInfo> infos = lectureServiceImpl.getAllLectures();
        return infos.stream().map(item -> LectureSearchResponse.from(item)).collect(Collectors.toList());
    }
}
