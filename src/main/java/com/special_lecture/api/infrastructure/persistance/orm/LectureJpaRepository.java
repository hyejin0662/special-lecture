package com.special_lecture.api.infrastructure.persistance.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.special_lecture.api.business.model.entity.Lecture;

@Repository
public interface LectureJpaRepository extends JpaRepository<Lecture, Long> {
}