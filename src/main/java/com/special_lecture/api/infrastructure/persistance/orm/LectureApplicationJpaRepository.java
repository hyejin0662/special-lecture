package com.special_lecture.api.infrastructure.persistance.orm;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.special_lecture.api.business.model.entity.LectureApplication;

public interface LectureApplicationJpaRepository extends JpaRepository<LectureApplication, Long> {

    @Query("SELECT la FROM LectureApplication la WHERE la.userId = :userId AND la.lecture.lectureId = :lectureId")
    Optional<LectureApplication> findByUserIdAndLectureId(@Param("userId") String userId, @Param("lectureId") Long lectureId);

    @Query("SELECT COUNT(la) FROM LectureApplication la WHERE la.lecture.lectureId = :lectureId")
    int countByLectureId(@Param("lectureId") Long lectureId);

    boolean existsByUserIdAndLectureId(String userId, Long lectureId);
}