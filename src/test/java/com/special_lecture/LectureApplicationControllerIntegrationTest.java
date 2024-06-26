package com.special_lecture;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.special_lecture.api.application.dto.request.LectureApplicationRequest;
import com.special_lecture.api.application.dto.response.LectureApplicationResponse;
import com.special_lecture.api.business.model.dto.LectureCommand;
import com.special_lecture.api.business.model.dto.LectureInfo;
import com.special_lecture.api.business.model.entity.Lecture;
import com.special_lecture.api.business.model.entity.LectureApplication;
import com.special_lecture.api.business.repo.LectureApplicationRepository;
import com.special_lecture.api.business.repo.LectureRepository;
import com.special_lecture.api.infrastructure.persistance.orm.LectureApplicationJpaRepository;
import com.special_lecture.api.infrastructure.persistance.orm.LectureJpaRepository;
import com.special_lecture.common.type.LectureStatus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class LectureApplicationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private LectureJpaRepository lectureJpaRepository;

    @Autowired
    private LectureApplicationJpaRepository lectureApplicationJpaRepository;

    @BeforeEach
    void setUp() {
        lectureApplicationJpaRepository.deleteAll();
        lectureJpaRepository.deleteAll();

        Lecture lecture = new Lecture();
        LectureInfo.from(lecture);

//        lecture.setLectureId(1L);
//        lecture.setTopic("Spring Boot Lecture");
//        lecture.setDescription("Learn Spring Boot");
//        lecture.setInstructor("John Doe");
//        lecture.setLectureStatus(LectureStatus.PENDING);
//        lecture.setCreateAt(LocalDateTime.now());
//        lecture.setUpdateAt(LocalDateTime.now());
//        lecture.setVersion(1L);
//        lecture.setCapacity(100);

        lectureJpaRepository.save(lecture);
    }

    @Test
    void applyForLecture_success() throws Exception {
        // Arrange
        LectureApplicationRequest request = new LectureApplicationRequest();
        request.setUserId(1L);
        request.setLectureId(1L);

        // Act & Assert
        mockMvc.perform(post("/api/lecture-applications")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.version").value(1L));
    }

    @Test
    void checkApplicationStatus_applicationExists() throws Exception {
        // Arrange
        Lecture lecture = lectureJpaRepository.findById(1L).orElseThrow();
        LectureApplication application = new LectureApplication(1L, lecture);
        lectureApplicationJpaRepository.save(application);

        // Act & Assert
        mockMvc.perform(get("/api/lecture-applications/status")
                .param("userId", "1")
                .param("lectureId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.version").value(1L));
    }

    @Test
    void checkApplicationStatus_applicationDoesNotExist() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/api/lecture-applications/status")
                .param("userId", "1")
                .param("lectureId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.version").doesNotExist());
    }
}
