package com.special_lecture.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.special_lecture.domain.dto.LectureDto;
import com.special_lecture.repository.LectureRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;

    public List<LectureDto> getAllLectures() {
        return lectureRepository.findAll().stream()
            .map(lecture -> new LectureDto(
                lecture.getLectureId(),
                lecture.getTopic(),
                lecture.getCreateAt(),
                lecture.getUpdateAt(),
                lecture.getCapacity(),
                (int)lectureRepository.count()
            ))
            .collect(Collectors.toList());
    }
}