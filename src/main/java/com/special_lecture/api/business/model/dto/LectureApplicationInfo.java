package com.special_lecture.api.business.model.dto;

import com.special_lecture.api.business.model.entity.LectureApplication;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LectureApplicationInfo {
  private Long applicationId;
  private Long userId;
  private Long lectureId;
  private LocalDateTime applicationDate;
  private Long version;

  public static LectureApplicationInfo from(LectureApplication application) {
    return LectureApplicationInfo.builder()
        .applicationId(application.getLectureApplicationId())
        .userId(application.getUserId())
        .lectureId(application.getLecture().getLectureId())
        .version(application.getVersion())
        .build();
  }
}