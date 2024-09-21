package com.special_lecture.api.business.model.dto;

import com.special_lecture.api.business.model.entity.LectureApplication;
import com.special_lecture.common.mapper.DtoConverter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class LectureApplicationCommand {
  private String userId;
  private Long lectureId;

  public LectureApplication toEntity() {
    return DtoConverter.convert(this, LectureApplication.class);
  }

}