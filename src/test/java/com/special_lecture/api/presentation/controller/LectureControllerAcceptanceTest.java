package com.special_lecture.api.presentation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.special_lecture.api.application.dto.request.LectureApplicationRequest;
import com.special_lecture.api.application.dto.response.LectureApplicationApplyResponse;
import com.special_lecture.api.application.dto.response.LectureApplicationStatusResponse;
import com.special_lecture.api.application.dto.response.LectureSearchResponse;
import com.special_lecture.common.model.WebResponseData;
import com.special_lecture.common.type.GlobalResponseCode;
import com.special_lecture.common.type.LectureStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class LectureControllerAcceptanceTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;


	@DisplayName("[API][POST] 강의 신청 - 정상 호출")
	@Test
	@Sql(scripts = "/test-data.sql")
	void givenValidRequest_whenApplyingForLecture_thenReturnsOk() throws Exception {
		// Given
		LectureApplicationRequest request = new LectureApplicationRequest("아직등록하지않은유저", 1L);

		// When & Then
		mvc.perform(post("/lectures/apply")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.applicationId", is(8)))
			.andExpect(jsonPath("$.userId", is("아직등록하지않은유저")))
			.andExpect(jsonPath("$.lectureId", is(1)));
	}



	@DisplayName("[API][GET] 강의 신청 상태 조회 - 정상 호출")
	@Test
	@Sql(scripts = "/test-data.sql")
	void givenValidRequest_whenCheckingApplicationStatus_thenReturnsOk() throws Exception {
		// Given
		String userId = "user1";
		Long lectureId = 1L;

		// When & Then
		mvc.perform(get("/lectures/application/{userId}/{lectureId}", userId, lectureId)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.userId", is(userId)))
			.andExpect(jsonPath("$.success", is(true)));
	}


	@DisplayName("[API][GET] 모든 강의 조회 - 정상 호출")
	@Test
	@Sql(scripts = "/test-data.sql")
	void givenNothing_whenGettingAllLectures_thenReturnsAllLectures() throws Exception {
		// When & Then
		mvc.perform(get("/lectures")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.code", is(GlobalResponseCode.SUCCESS_CODE.name())))
			.andExpect(jsonPath("$.data[0].lectureId", is(1)))
			.andExpect(jsonPath("$.data[0].topic", is("AI 소개")))
			.andExpect(jsonPath("$.data[0].instructor", is("김민수 박사")))
			.andExpect(jsonPath("$.data[0].description", is("인공지능의 기본 개념과 응용")))
			.andExpect(jsonPath("$.data[0].lectureStatus", is("PENDING")))
			.andExpect(jsonPath("$.data[0].capacity", is(50)));
	}





	@DisplayName("[API][POST] 동시성 테스트 - 강의 신청")
	@Test
	@Sql(scripts = "/test-data-concurrency.sql")
	void givenConcurrentRequests_whenApplyingForLecture_thenHandlesConcurrencyCorrectly() throws Exception {
		int numberOfThreads = 31; // 30명은 성공하고 1명은 실패해야 함
		ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
		CountDownLatch latch = new CountDownLatch(numberOfThreads);

		for (int i = 1; i <= numberOfThreads; i++) {
			String userId = "user" + i;
			executorService.submit(() -> {
				try {
					LectureApplicationRequest request = new LectureApplicationRequest(userId, 1L);
					mvc.perform(post("/lectures/apply")
							.contentType(MediaType.APPLICATION_JSON)
							.content(objectMapper.writeValueAsString(request)))
						.andExpect(status().isOk());
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					latch.countDown();
				}
			});
		}

		latch.await(); // 모든 스레드가 작업을 마칠 때까지 대기

		// 성공적으로 신청된 강의 수가 30인지 확인
		mvc.perform(get("/lectures")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data[0].lectureId", is(1)))
			.andExpect(jsonPath("$.data[0].capacity", is(30)))
			.andExpect(jsonPath("$.data[0].lectureStatus", is("PENDING")));

		executorService.shutdown();
	}

}
