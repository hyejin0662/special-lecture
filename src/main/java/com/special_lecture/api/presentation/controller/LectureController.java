package com.special_lecture.api.presentation.controller;

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

    private final LectureFacade lectureApplyFacade;
    private final LectureService lectureService;

//    @PostMapping("/apply")
//    public ResponseEntity<LectureApplicationResponse> applyForLecture(@Valid @RequestBody LectureApplicationRequest request) {
//        LectureApplicationResponse response = lectureApplyFacade.applyForLecture(request);
//        return ResponseEntity.ok(response);
//    }
//
//    @GetMapping("/application/{userId}/{lectureId}")
//    public ResponseEntity<LectureApplicationResponse> checkApplicationStatus(@PathVariable Long userId, @PathVariable Long lectureId) {
//        LectureApplicationResponse response = lectureApplyFacade.checkApplicationStatus(userId, lectureId);
//        return ResponseEntity.ok(response);
//    }

    @GetMapping
    public ResponseEntity<WebResponseData<List<LectureInfo>>> getAllLectures() {
        List<LectureInfo> response = lectureService.getAllLectures();
        return ResponseEntity.ok(WebResponseData.ok(response));
    }
}