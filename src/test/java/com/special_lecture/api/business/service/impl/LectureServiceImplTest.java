package com.special_lecture.api.business.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.special_lecture.api.business.model.dto.LectureInfo;
import com.special_lecture.api.business.model.entity.Lecture;
import com.special_lecture.api.business.repo.LectureRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class LectureServiceImplTest {

  @Mock
  private LectureRepository lectureRepository;

  @InjectMocks
  private LectureServiceImpl lectureServiceImpl;

  private List<Lecture> lectures;

  @BeforeEach
  void setUp() {
    lectures = new ArrayList<>();
    IntStream.rangeClosed(1, 5).forEach(i -> {
      Lecture lecture = new Lecture();
      lecture.updateLectureId((long) i);
      lectures.add(lecture);
    });
  }

  @Test
  @DisplayName("모든 강의 조회 테스트")
  @Description("모든 강의를 조회하는 기능의 테스트")
  void testGetAllLectures() {
    when(lectureRepository.findAll()).thenReturn(lectures);

    List<LectureInfo> lectureInfos = lectureServiceImpl.getAllLectures();

    assertEquals(5, lectureInfos.size());

    IntStream.rangeClosed(0, 4).forEach(i -> {
      Lecture lecture = lectures.get(i);
      LectureInfo lectureInfo = lectureInfos.get(i);
      assertEquals(lecture.getLectureId(), lectureInfo.getLectureId());
    });
  }
}