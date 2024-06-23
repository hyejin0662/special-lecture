package com.special_lecture.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.special_lecture.domain.dto.LectureDto;
import com.special_lecture.domain.dto.request.LectureApplicationRequest;
import com.special_lecture.domain.dto.response.LectureApplicationResponse;
import com.special_lecture.domain.model.WebResponseData;
import com.special_lecture.service.LectureApplicationService;
import com.special_lecture.service.LectureService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/lectures")
@RequiredArgsConstructor
public class LectureController {

    private final LectureApplicationService lectureApplicationService;
    private final LectureService lectureService;

    @PostMapping("/apply")
    public WebResponseData<LectureApplicationResponse> applyForLecture(@RequestBody LectureApplicationRequest request) {
        LectureApplicationResponse response = lectureApplicationService.applyForLecture(request);
        // return new ResponseEntity<>(response, HttpStatus.OK);
        return WebResponseData.ok(response);
    }

    @GetMapping("/application/{userId}/{lectureId}")
    public WebResponseData<LectureApplicationResponse> checkApplicationStatus(@PathVariable Long userId, @PathVariable Long lectureId) {
        LectureApplicationResponse response = lectureApplicationService.checkApplicationStatus(userId, lectureId);
        return WebResponseData.ok(response);
    }

    @GetMapping
    public WebResponseData<List<LectureDto>> getAllLectures() {
        List<LectureDto> response = lectureService.getAllLectures();
        return WebResponseData.ok(response);
    }
}