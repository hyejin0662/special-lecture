package com.special_lecture.domain.entity;

import java.time.LocalDateTime;

import com.special_lecture.domain.type.LectureStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lecture")
@NoArgsConstructor
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

    public Lecture(String topic, String description, String instructor, LectureStatus lectureStatus, LocalDateTime createAt, LocalDateTime updateAt) {
        this.topic = topic;
        this.description = description;
        this.instructor = instructor;
        this.lectureStatus = lectureStatus;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }
    public boolean isFull(int registeredCount) {
        return registeredCount >= capacity;
    }
}