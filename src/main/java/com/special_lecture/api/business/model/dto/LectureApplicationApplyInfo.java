package com.special_lecture.api.business.model.dto;

import com.special_lecture.api.business.model.entity.LectureApplication;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LectureApplicationApplyInfo {
  private Long applicationId;
  private String userId;
  private Long lectureId;
  private LocalDateTime applicationDate;




  public static LectureApplicationApplyInfo from(LectureApplication application) {
    return LectureApplicationApplyInfo.builder()
        .applicationId(application.getLectureApplicationId())
        .userId(application.getUserId())
        .lectureId(application.getLecture().getLectureId())
        .build();
  }
}