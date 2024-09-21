package com.special_lecture.api.presentation.controller;

import com.special_lecture.api.application.dto.response.LectureApplicationStatusResponse;
import com.special_lecture.api.application.dto.response.LectureSearchResponse;
import com.special_lecture.api.application.facade.LectureFacade;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.special_lecture.api.application.dto.request.LectureApplicationRequest;
import com.special_lecture.api.application.dto.response.LectureApplicationApplyResponse;
import com.special_lecture.api.business.model.dto.LectureInfo;
import com.special_lecture.common.model.WebResponseData;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/lectures")
@RequiredArgsConstructor
public class LectureController {

    private final LectureFacade lectureFacade;

   @PostMapping("/apply")
   public ResponseEntity<LectureApplicationApplyResponse> applyForLecture(@Valid @RequestBody LectureApplicationRequest request) {
       LectureApplicationApplyResponse response = lectureFacade.applyForLecture(request);
       return ResponseEntity.ok(response);
   }

   @GetMapping("/application/{userId}/{lectureId}")
   public ResponseEntity<LectureApplicationStatusResponse> checkApplicationStatus(@PathVariable String userId, @PathVariable Long lectureId) {
       LectureApplicationStatusResponse response = lectureFacade.checkApplicationStatus(userId, lectureId);
       return ResponseEntity.ok(response);
   }

    @GetMapping
    public ResponseEntity<WebResponseData<List<LectureSearchResponse>>> getAllLectures() {
        List<LectureSearchResponse> response = lectureFacade.getAllLectures();
        return ResponseEntity.ok(WebResponseData.ok(response));
    }
}