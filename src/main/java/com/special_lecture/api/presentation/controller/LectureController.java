package com.special_lecture.api.presentation.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.special_lecture.api.application.dto.request.LectureApplicationRequest;
import com.special_lecture.api.application.dto.response.LectureApplicationResponse;
import com.special_lecture.api.business.model.dto.LectureCommand;
import com.special_lecture.api.business.model.dto.LectureInfo;
import com.special_lecture.common.mapper.DtoConverter;
import com.special_lecture.common.model.WebResponseData;
import com.special_lecture.api.business.service.LectureApplicationService;
import com.special_lecture.api.business.service.LectureService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/lectures")
@RequiredArgsConstructor
public class LectureController {

    private final LectureApplicationService lectureApplicationService;
    private final LectureService lectureService;

    @PostMapping("/apply")
    public WebResponseData<LectureApplicationResponse> applyForLecture(@Valid @RequestBody LectureApplicationRequest request) {
        // DtoConverter를 사용하여 LectureCommand로 변환
        LectureCommand command = DtoConverter.convert(request, LectureCommand.class);
        LectureApplicationResponse response = lectureApplicationService.applyForLecture(command);
        return WebResponseData.ok(response);
    }

    @GetMapping("/application/{userId}/{lectureId}")
    public WebResponseData<LectureApplicationResponse> checkApplicationStatus(@PathVariable Long userId, @PathVariable Long lectureId) {
        LectureApplicationResponse response = lectureApplicationService.checkApplicationStatus(userId, lectureId);
        return WebResponseData.ok(response);
    }

    @GetMapping
    public WebResponseData<List<LectureInfo>> getAllLectures() {
        List<LectureInfo> response = lectureService.getAllLectures();
        return WebResponseData.ok(response);
    }
}