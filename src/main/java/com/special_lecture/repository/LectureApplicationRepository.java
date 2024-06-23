package com.special_lecture.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.special_lecture.domain.entity.LectureApplication;

@Repository
public interface LectureApplicationRepository extends JpaRepository<LectureApplication, Long> {
    // Optional<LectureApplication> findByUserIdAndLectureId(Long userId, Long lectureId);

    @Query("SELECT la FROM LectureApplication la WHERE la.userId = :userId AND la.lecture.lectureId = :lectureId")
    Optional<LectureApplication> findByUserIdAndLectureId(@Param("userId") Long userId, @Param("lectureId") Long lectureId);

    @Query("SELECT COUNT(la) FROM LectureApplication la WHERE la.lecture.lectureId = :lectureId")
    int countByLectureId(@Param("lectureId") Long lectureId);
}