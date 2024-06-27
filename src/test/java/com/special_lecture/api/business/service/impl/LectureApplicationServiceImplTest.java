package com.special_lecture.api.business.service.impl;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.special_lecture.api.business.model.dto.LectureApplicationApplyInfo;
import com.special_lecture.api.business.model.dto.LectureApplicationCommand;
import com.special_lecture.api.business.model.dto.LectureApplicationStatusInfo;
import com.special_lecture.api.business.model.entity.Lecture;
import com.special_lecture.api.business.model.entity.LectureApplication;
import com.special_lecture.api.business.repo.LectureApplicationRepository;
import com.special_lecture.api.business.repo.LectureRepository;
import com.special_lecture.api.business.vaildatior.LectureValidator;
import com.special_lecture.common.exception.LectureException;
import com.special_lecture.common.type.GlobalResponseCode;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LectureApplicationServiceImplTest {

  @Mock
  private LectureValidator lectureValidator;

  @Mock
  private LectureApplicationRepository lectureApplicationRepository;

  @Mock
  private LectureRepository lectureRepository;

  @InjectMocks
  private LectureApplicationServiceImpl lectureApplicationServiceImpl;

  private LectureApplicationCommand command;
  private Lecture lecture;

  @BeforeEach
  void setUp() {
    command = new LectureApplicationCommand("userId", 1L);
    lecture = new Lecture();
    lecture.fromLectureId(1L);
  }

  @Test
  @DisplayName("강의 신청 성공 테스트")
  @Description("강의를 성공적으로 신청할 때의 테스트")
  void testApplyForLecture_Success() {
    when(lectureRepository.findByIdWithLock(command.getLectureId())).thenReturn(lecture);
    doNothing().when(lectureValidator).validateLectureCapacity(any(Lecture.class));
    doNothing().when(lectureValidator).validateUserNotAlreadyApplied(anyString(), anyLong());

    doAnswer(invocation -> {
      LectureApplication application = invocation.getArgument(0);
      application = new LectureApplication("userId", lecture);
      return null;
    }).when(lectureApplicationRepository).save(any(LectureApplication.class));

    LectureApplicationApplyInfo result = lectureApplicationServiceImpl.applyForLecture(command);

    assertNotNull(result);
    assertEquals("userId", result.getUserId());
    verify(lectureRepository, times(1)).findByIdWithLock(command.getLectureId());
    verify(lectureValidator, times(1)).validateLectureCapacity(lecture);
    verify(lectureValidator, times(1)).validateUserNotAlreadyApplied("userId", 1L);
    verify(lectureApplicationRepository, times(1)).save(any(LectureApplication.class));
  }

  @Test
  @DisplayName("강의 신청 실패 - 강의 없음")
  @Description("강의가 존재하지 않을 때의 강의 신청 실패 테스트")
  void testApplyForLecture_LectureNotFound() {
    when(lectureRepository.findByIdWithLock(command.getLectureId())).thenThrow(new LectureException(GlobalResponseCode.NOT_FOUND_LECTURE));

    LectureException exception = assertThrows(LectureException.class, () ->
        lectureApplicationServiceImpl.applyForLecture(command)
    );

    assertEquals(GlobalResponseCode.NOT_FOUND_LECTURE, exception.getGlobalResponseCode());
    verify(lectureRepository, times(1)).findByIdWithLock(command.getLectureId());
    verify(lectureValidator, times(0)).validateLectureCapacity(any(Lecture.class));
    verify(lectureValidator, times(0)).validateUserNotAlreadyApplied(anyString(), anyLong());
    verify(lectureApplicationRepository, times(0)).save(any(LectureApplication.class));
  }

  @Test
  @DisplayName("신청 상태 확인 테스트")
  @Description("사용자의 강의 신청 상태를 확인하는 테스트")
  void testCheckApplicationStatus() {
    when(lectureApplicationRepository.existsByUserIdAndLectureId("userId", 1L)).thenReturn(true);

    LectureApplicationStatusInfo result = lectureApplicationServiceImpl.checkApplicationStatus("userId", 1L);

    assertNotNull(result);
    assertEquals("userId", result.getUserId());
    assertTrue(result.isSuccess());
    verify(lectureApplicationRepository, times(1)).existsByUserIdAndLectureId("userId", 1L);
  }
}