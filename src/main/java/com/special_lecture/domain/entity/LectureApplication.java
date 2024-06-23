package com.special_lecture.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lecture_application")
@NoArgsConstructor
@Getter
public class LectureApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureApplicationId;

    @Column(nullable = false)
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "lecture_id", nullable = false)
    private Lecture lecture;

    public LectureApplication(Long userId, Lecture lecture) {
        this.userId = userId;
        this.lecture = lecture;
    }

}