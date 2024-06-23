package com.special_lecture.repository.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.special_lecture.domain.entity.LectureApplication;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;

@Repository
public class LectureApplicationRepositoryImpl {

    private final EntityManager entityManager;

    public LectureApplicationRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void saveWithPessimisticLock(LectureApplication application) {
        entityManager.lock(application, LockModeType.PESSIMISTIC_WRITE);
        entityManager.persist(application);
    }
}