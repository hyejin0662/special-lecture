package com.special_lecture.api.infrastructure.persistance.orm;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.special_lecture.api.business.model.entity.Lecture;

import jakarta.persistence.LockModeType;

public interface LectureJpaRepository extends JpaRepository<Lecture, Long> {

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("SELECT l FROM Lecture l WHERE l.lectureId = :lectureId")
	Optional<Lecture> findByIdWithLock(@Param("lectureId") Long lectureId);
}

