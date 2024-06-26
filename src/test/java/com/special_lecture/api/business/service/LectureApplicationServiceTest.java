//package com.special_lecture.api.business.service;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import static org.mockito.Mockito.*;
//
//import com.special_lecture.common.exception.LectureException;
//import com.special_lecture.common.type.GlobalResponseCode;
//import java.util.Optional;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import com.special_lecture.api.business.model.dto.LectureCommand;
//import com.special_lecture.api.business.model.entity.Lecture;
//import com.special_lecture.api.business.model.entity.LectureApplication;
//import com.special_lecture.api.business.repo.LectureApplicationRepository;
//import com.special_lecture.api.business.vaildatior.LectureValidator;
//import com.special_lecture.api.application.dto.response.LectureApplicationResponse;
//
//@ExtendWith(MockitoExtension.class)
//class LectureApplicationServiceTest {
//
//  @Mock
//  private LectureValidator lectureValidator;
//
//  @Mock
//  private LectureApplicationRepository lectureApplicationRepository;
//
//  @InjectMocks
//  private LectureApplicationService lectureApplicationService;
//
//  private LectureCommand command;
//  private Lecture lecture;
//  private LectureApplication application;
//
//  @BeforeEach
//  void setUp() {
//    command = new LectureCommand();
//    command.setUserId(1L);
//    command.setLectureId(1L);
//
//    lecture = new Lecture();
//
//    application = new LectureApplication(command.getUserId(), lecture);
//  }
//
//  @Test
//  void applyForLecture_success() {
//    // Arrange
//    when(lectureValidator.validateLectureExists(command.getLectureId())).thenReturn(lecture);
//    doNothing().when(lectureValidator).validateLectureCapacity(lecture);
//    doNothing().when(lectureValidator).validateUserNotAlreadyApplied(command.getUserId(), command.getLectureId());
//    doNothing().when(lectureApplicationRepository).saveWithOptimisticLock(application);
//
//    // Act
//    LectureApplicationResponse response = lectureApplicationService.applyForLecture(command);
//
//    // Assert
//    assertTrue(response.isSuccess());
//    assertEquals(lecture.getVersion(), response.getVersion());
//  }
//
//  @Test
//  void applyForLecture_lectureNotFound() {
//    // Arrange
//    when(lectureValidator.validateLectureExists(command.getLectureId())).thenThrow(new LectureException(
//        GlobalResponseCode.NOT_FOUND_LECTURE));
//
//    // Act & Assert
//    LectureException exception = assertThrows(LectureException.class, () -> {
//      lectureApplicationService.applyForLecture(command);
//    });
//    assertEquals(GlobalResponseCode.NOT_FOUND_LECTURE, exception.getGlobalResponseCode());
//  }
//
//  @Test
//  void checkApplicationStatus_applicationExists() {
//    // Arrange
//    when(lectureApplicationRepository.findByUserIdAndLectureId(command.getUserId(), command.getLectureId())).thenReturn(Optional.of(application));
//
//    // Act
//    LectureApplicationResponse response = lectureApplicationService.checkApplicationStatus(command.getUserId(), command.getLectureId());
//
//    // Assert
//    assertTrue(response.isSuccess());
//    assertEquals(lecture.getVersion(), response.getVersion());
//  }
//
//  @Test
//  void checkApplicationStatus_applicationDoesNotExist() {
//    // When
//    when(lectureApplicationRepository.findByUserIdAndLectureId(command.getUserId(), command.getLectureId())).thenReturn(Optional.empty());
//
//    // Act
//    LectureApplicationResponse response = lectureApplicationService.checkApplicationStatus(command.getUserId(), command.getLectureId());
//
//    // Assert
//    assertFalse(response.isSuccess());
//    assertNull(response.getVersion());
//  }
//}
