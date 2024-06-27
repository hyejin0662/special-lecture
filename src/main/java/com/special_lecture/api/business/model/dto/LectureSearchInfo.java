package com.special_lecture.api.business.model.dto;

import com.special_lecture.api.business.model.entity.Lecture;
import com.special_lecture.common.mapper.DtoConverter;
import com.special_lecture.common.type.LectureStatus;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LectureSearchInfo {

  private Long lectureId;
  private String topic;
  private String description;
  private String instructor;
  private LectureStatus lectureStatus;
  private LocalDateTime createAt;
  private LocalDateTime updateAt;
  private int capacity;

  public static LectureSearchInfo from(Lecture lecture) {
    return DtoConverter.convert(lecture, LectureSearchInfo.class);
  }

}
