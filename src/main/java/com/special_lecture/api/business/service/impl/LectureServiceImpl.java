package com.special_lecture.api.business.service.impl;

import com.special_lecture.api.business.service.LectureService;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.special_lecture.api.business.model.dto.LectureInfo;
import com.special_lecture.api.business.repo.LectureRepository;
import com.special_lecture.common.mapper.DtoConverter;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LectureServiceImpl implements LectureService {

    private final LectureRepository lectureRepository;

    @Override
    @Transactional(readOnly = true)
    public List<LectureInfo> getAllLectures() {
        return lectureRepository.findAll().stream()
            .map(lecture -> DtoConverter.convert(lecture, LectureInfo.class))
            .collect(Collectors.toList());
    }
}