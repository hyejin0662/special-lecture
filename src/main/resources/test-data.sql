-- 기존 테이블 삭제
DROP TABLE IF EXISTS lecture_application;
DROP TABLE IF EXISTS lecture_registration;
DROP TABLE IF EXISTS lecture;

-- lecture 테이블 생성
CREATE TABLE lecture (
                         lecture_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         topic VARCHAR(255) NOT NULL,
                         description TEXT NOT NULL,
                         instructor VARCHAR(255) NOT NULL,
                         lecture_status ENUM('PENDING', 'ONGOING', 'COMPLETED', 'CANCELLED') NOT NULL,
                         created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         capacity INT NOT NULL DEFAULT 30
);

-- lecture_application 테이블 생성
CREATE TABLE lecture_application (
                                     lecture_application_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     user_id VARCHAR(255) NOT NULL,
                                     lecture_id BIGINT NOT NULL,
                                     applied_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                     updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                     FOREIGN KEY (lecture_id) REFERENCES lecture(lecture_id)
);

-- lecture_registration 테이블 생성
CREATE TABLE lecture_registration (
                                      registration_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      user_id VARCHAR(255) NOT NULL,
                                      lecture_id BIGINT NOT NULL,
                                      registration_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                      FOREIGN KEY (lecture_id) REFERENCES lecture(lecture_id)
);

-- 더미 데이터 삽입
-- lecture 테이블 데이터
INSERT INTO lecture (topic, description, instructor, lecture_status, created_at, updated_at, capacity)
VALUES
    ('AI 소개', '인공지능의 기본 개념과 응용', '김민수 박사', 'PENDING', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 50),
    ('고급 자바 프로그래밍', '고급 자바 프로그래밍 기술 심화', '이재영 교수', 'ONGOING', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 40),
    ('파이썬을 활용한 데이터 과학', '파이썬을 활용한 데이터 분석 및 시각화 학습', '박지훈 박사', 'COMPLETED', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 35),
    ('웹 개발 기초', 'HTML, CSS, JavaScript 초보자용 입문', '정우성 선생님', 'PENDING', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 45),
    ('머신러닝', '머신러닝 알고리즘 개요 및 응용', '최유진 선생님', 'ONGOING', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 30);

-- lecture_application 테이블 데이터
INSERT INTO lecture_application (user_id, lecture_id, applied_at, updated_at)
VALUES
    ('user1', 1, NOW(), NOW()),
    ('user2', 2, NOW(), NOW()),
    ('user3', 3, NOW(), NOW()),
    ('user4', 4, NOW(), NOW()),
    ('user5', 5, NOW(), NOW()),
    ('user1', 2, NOW(), NOW()),
    ('user2', 3, NOW(), NOW());

-- lecture_registration 테이블 데이터
INSERT INTO lecture_registration (user_id, lecture_id, registration_date)
VALUES
    ('user1', 1, CURRENT_TIMESTAMP),
    ('user2', 2, CURRENT_TIMESTAMP),
    ('user3', 3, CURRENT_TIMESTAMP),
    ('user4', 4, CURRENT_TIMESTAMP),
    ('user5', 5, CURRENT_TIMESTAMP),
    ('user1', 2, CURRENT_TIMESTAMP),
    ('user2', 3, CURRENT_TIMESTAMP);
