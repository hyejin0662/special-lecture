package com.special_lecture.api.business.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.special_lecture.api.business.model.dto.LectureInfo;
import com.special_lecture.api.infrastructure.persistance.orm.LectureJpaRepository;
import com.special_lecture.common.mapper.DtoConverter;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureJpaRepository lectureJpaRepository;

    public List<LectureInfo> getAllLectures() {
        return lectureJpaRepository.findAll().stream()
            .map(lecture -> DtoConverter.convert(lecture, LectureInfo.class))
            .collect(Collectors.toList());
    }
}