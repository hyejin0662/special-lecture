package com.special_lecture.api.business.vaildatior;

import com.special_lecture.api.business.model.entity.Lecture;
import com.special_lecture.api.business.model.entity.LectureApplication;
import com.special_lecture.api.infrastructure.persistance.orm.LectureApplicationJpaRepository;
import com.special_lecture.api.infrastructure.persistance.orm.LectureJpaRepository;
import com.special_lecture.common.exception.LectureException;
import com.special_lecture.common.type.GlobalResponseCode;
import com.special_lecture.common.type.LectureStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LectureValidatorTest {

	@Mock
	private LectureJpaRepository lectureJpaRepository;

	@Mock
	private LectureApplicationJpaRepository lectureApplicationJpaRepository;

	@InjectMocks
	private LectureValidator lectureValidator;

	private Lecture lecture;

	@BeforeEach
	void setUp() {
		lecture = new Lecture(
			1L,
			"Test Topic",
			"Test Description",
			"Test Instructor",
			LectureStatus.PENDING,
			LocalDateTime.now(),
			LocalDateTime.now(),
			30
		);
	}

	@DisplayName("강의 정원 확인 - 성공")
	@Test
	void validateLectureCapacity_success() {
		// Given
		when(lectureApplicationJpaRepository.countByLectureId(anyLong())).thenReturn(29);

		// When & Then
		lectureValidator.validateLectureCapacity(lecture);

		// Verify
		verify(lectureApplicationJpaRepository).countByLectureId(anyLong());
	}

	@DisplayName("강의 정원 확인 - 실패")
	@Test
	void validateLectureCapacity_full() {
		// Given
		when(lectureApplicationJpaRepository.countByLectureId(anyLong())).thenReturn(30);

		// When & Then
		assertThrows(LectureException.class, () -> lectureValidator.validateLectureCapacity(lecture));

		// Verify
		verify(lectureApplicationJpaRepository).countByLectureId(anyLong());
	}

	@DisplayName("중복 신청 확인 - 성공")
	@Test
	void validateUserNotApplied_success() {
		// Given
		when(lectureApplicationJpaRepository.findByUserIdAndLectureId(eq("user1"), anyLong())).thenReturn(Optional.empty());

		// When & Then
		lectureValidator.validateUserNotAlreadyApplied("user1", 1L);

		// Verify
		verify(lectureApplicationJpaRepository).findByUserIdAndLectureId(eq("user1"), anyLong());
	}

	@DisplayName("중복 신청 확인 - 실패")
	@Test
	void validateUserNotApplied_alreadyApplied() {
		// Given
		LectureApplication lectureApplication = new LectureApplication();
		when(lectureApplicationJpaRepository.findByUserIdAndLectureId(eq("user1"), anyLong())).thenReturn(Optional.of(lectureApplication));

		// When & Then
		assertThrows(LectureException.class, () -> lectureValidator.validateUserNotAlreadyApplied("user1", 1L));

		// Verify
		verify(lectureApplicationJpaRepository).findByUserIdAndLectureId(eq("user1"), anyLong());
	}
}
