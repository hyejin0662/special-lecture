package com.special_lecture;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.special_lecture.api.application.dto.request.LectureApplicationRequest;
import com.special_lecture.api.application.dto.response.LectureApplicationApplyResponse;
import com.special_lecture.api.application.dto.response.LectureApplicationStatusResponse;
import com.special_lecture.api.application.dto.response.LectureSearchResponse;
import com.special_lecture.api.business.model.entity.Lecture;
import com.special_lecture.api.business.model.entity.LectureApplication;
import com.special_lecture.api.business.repo.LectureApplicationRepository;
import com.special_lecture.api.business.repo.LectureRepository;
import com.special_lecture.common.model.WebResponseData;
import com.special_lecture.common.type.LectureStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LectureApplicationIntegrationTest {

  @Autowired
  private MockMvc mockMvc;
  private Lecture lecture;

  @MockBean
  private LectureRepository lectureRepository;

  @MockBean
  private LectureApplicationRepository lectureApplicationRepository;

  @Autowired
  private ObjectMapper objectMapper;

  private LectureApplicationRequest lectureApplicationRequest;
  private LectureApplicationApplyResponse lectureApplicationApplyResponse;
  private LectureApplicationStatusResponse lectureApplicationStatusResponse;
  private List<LectureSearchResponse> lectureSearchResponses;

  @BeforeEach
  void setUp() {
    lecture = new Lecture(
        1L,
        "Test Lecture",
        "Test Description",
        "Test Instructor",
        LectureStatus.PENDING,
        LocalDateTime.now(),
        LocalDateTime.now(),
        30
    );

    lectureApplicationRequest = new LectureApplicationRequest();
    lectureApplicationRequest.setUserId("userId");
    lectureApplicationRequest.setLectureId(1L);

    lectureApplicationApplyResponse = LectureApplicationApplyResponse.builder()
        .userId("userId")
        .applicationId(1L)
        .build();

    lectureApplicationStatusResponse = LectureApplicationStatusResponse.builder()
        .userId("userId")
        .success(true)
        .build();

    lectureSearchResponses = Arrays.asList(
        LectureSearchResponse.builder()
            .lectureId(1L)
            .topic("Lecture 1")
            .description("Description 1")
            .instructor("Instructor 1")
            .lectureStatus(LectureStatus.PENDING)
            .createAt(LocalDateTime.now())
            .updateAt(LocalDateTime.now())
            .capacity(30)
            .build(),
        LectureSearchResponse.builder()
            .lectureId(2L)
            .topic("Lecture 2")
            .description("Description 2")
            .instructor("Instructor 2")
            .lectureStatus(LectureStatus.PENDING)
            .createAt(LocalDateTime.now())
            .updateAt(LocalDateTime.now())
            .capacity(30)
            .build()
    );
  }

  @Test
  @DisplayName("강의 신청 통합 테스트")
  public void applyForLecture() throws Exception {
    when(lectureRepository.findByIdWithLock(lectureApplicationRequest.getLectureId())).thenReturn(lecture);
    doAnswer(invocation -> {
      LectureApplication application = invocation.getArgument(0);
      application = new LectureApplication("userId", lecture);
      return null;
    }).when(lectureApplicationRepository).save(any(LectureApplication.class));

    mockMvc.perform(post("/lectures/apply")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(lectureApplicationRequest)))
        .andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(lectureApplicationApplyResponse)));
  }

  @Test
  @DisplayName("강의 신청 상태 확인 통합 테스트")
  public void checkApplicationStatus() throws Exception {
    when(lectureApplicationRepository.existsByUserIdAndLectureId("userId", 1L)).thenReturn(true);

    mockMvc.perform(get("/lectures/application/userId/1"))
        .andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(lectureApplicationStatusResponse)));
  }

  @Test
  @DisplayName("모든 강의 조회 통합 테스트")
  public void getAllLectures() throws Exception {
    when(lectureRepository.findAll()).thenReturn(Arrays.asList(
        lecture,
        new Lecture(2L, "Lecture 2", "Description 2", "Instructor 2", LectureStatus.PENDING, LocalDateTime.now(), LocalDateTime.now(), 30)
    ));

    WebResponseData<List<LectureSearchResponse>> expectedResponse = WebResponseData.ok(lectureSearchResponses);

    mockMvc.perform(get("/lectures"))
        .andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)));
  }
}