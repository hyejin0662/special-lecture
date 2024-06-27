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
    ('동시성 테스트 강의', '동시성 제어 테스트를 위한 강의', '김동시 박사', 'PENDING', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 30);
