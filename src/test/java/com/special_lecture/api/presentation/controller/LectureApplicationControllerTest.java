//package com.special_lecture.api.presentation.controller;
//
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.mockito.Mockito.*;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.special_lecture.api.application.dto.request.LectureApplicationRequest;
//import com.special_lecture.api.application.dto.response.LectureApplicationResponse;
//import com.special_lecture.api.business.model.dto.LectureCommand;
//import com.special_lecture.api.business.service.LectureApplicationService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.http.MediaType;
//
//@ExtendWith(MockitoExtension.class)
//@WebMvcTest(LectureApplicationController.class)
//class LectureApplicationControllerTest {
//
//  @Autowired
//  private MockMvc mockMvc;
//
//  @MockBean
//  private LectureApplicationService lectureApplicationService;
//
//  @Autowired
//  private ObjectMapper objectMapper;
//
//  @Test
//  void applyForLecture_success() throws Exception {
//    // Arrange
//    LectureApplicationRequest request = new LectureApplicationRequest();
//    request.setUserId(1L);
//    request.setLectureId(1L);
//
//    LectureApplicationResponse response = new LectureApplicationResponse(true, 1L);
//
//    when(lectureApplicationService.applyForLecture(any(LectureCommand.class))).thenReturn(response);
//
//    // Act & Assert
//    mockMvc.perform(post("/api/lecture-applications")
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(objectMapper.writeValueAsString(request)))
//        .andExpect(status().isOk())
//        .andExpect(jsonPath("$.success").value(true))
//        .andExpect(jsonPath("$.version").value(1L));
//  }
//
//  @Test
//  void checkApplicationStatus_applicationExists() throws Exception {
//    // Arrange
//    Long userId = 1L;
//    Long lectureId = 1L;
//    LectureApplicationResponse response = new LectureApplicationResponse(true, 1L);
//
//    when(lectureApplicationService.checkApplicationStatus(userId, lectureId)).thenReturn(response);
//
//    // Act & Assert
//    mockMvc.perform(get("/api/lecture-applications/status")
//            .param("userId", userId.toString())
//            .param("lectureId", lectureId.toString()))
//        .andExpect(status().isOk())
//        .andExpect(jsonPath("$.success").value(true))
//        .andExpect(jsonPath("$.version").value(1L));
//  }
//
//  @Test
//  void checkApplicationStatus_applicationDoesNotExist() throws Exception {
//    // Arrange
//    Long userId = 1L;
//    Long lectureId = 1L;
//    LectureApplicationResponse response = new LectureApplicationResponse(false, null);
//
//    when(lectureApplicationService.checkApplicationStatus(userId, lectureId)).thenReturn(response);
//
//    // Act & Assert
//    mockMvc.perform(get("/api/lecture-applications/status")
//            .param("userId", userId.toString())
//            .param("lectureId", lectureId.toString()))
//        .andExpect(status().isOk())
//        .andExpect(jsonPath("$.success").value(false))
//        .andExpect(jsonPath("$.version").doesNotExist());
//  }
//}
