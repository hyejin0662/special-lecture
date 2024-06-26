package com.special_lecture.api.business.model.dto;

import com.special_lecture.api.business.model.entity.LectureApplication;
import com.special_lecture.common.mapper.DtoConverter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LectureApplicationCommand {
  private Long userId;
  private Long lectureId;

  public LectureApplication toEntity() {
    return DtoConverter.convert(this, LectureApplication.class);
  }
}