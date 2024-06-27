package com.special_lecture.api.business.model.entity;

import java.time.LocalDateTime;

import com.special_lecture.common.type.LectureStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lecture")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureId;

    @Column(nullable = false)
    private String topic;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String instructor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LectureStatus lectureStatus;

    @Column(nullable = false)
    private LocalDateTime createAt;

    @Column(nullable = false)
    private LocalDateTime updateAt;

    @Column(nullable = false)
    private int capacity = 30;

    public Lecture fromLectureId(Long lectureId) {
        this.lectureId = lectureId;
        return this;
    }
    public boolean isFull(int registeredCount) {
        return registeredCount >= capacity;
    }
}