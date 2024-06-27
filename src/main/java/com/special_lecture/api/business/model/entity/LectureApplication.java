package com.special_lecture.api.business.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.cglib.core.Local;

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
import lombok.Setter;

@Entity
@Table(name = "lecture_application")
@NoArgsConstructor
@Getter
@Setter
public class LectureApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureApplicationId;

    @Column(nullable = false)
    private String userId;

    @ManyToOne
    @JoinColumn(name = "lecture_id", nullable = false)
    private Lecture lecture;

    @Column(nullable = false)
    private LocalDateTime appliedAt;
    @Column(nullable = false)
    private LocalDateTime updatedAt;



    public LectureApplication(String userId, Lecture lecture) {
        this.userId = userId;
        this.lecture = lecture;
        this.appliedAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }



}