
DROP TABLE IF EXISTS lecture;
DROP TABLE IF EXISTS lecture_application;


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

CREATE TABLE lecture_application (
                                     lecture_application_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     user_id VARCHAR(255) NOT NULL,
                                     lecture_id BIGINT NOT NULL,
                                     created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                     FOREIGN KEY (lecture_id) REFERENCES lecture(lecture_id)
);
-- 중간 테이블 생성할지말지 고민중,,,
CREATE TABLE lecture_registration (
                                      registration_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      user_id VARCHAR(255) NOT NULL,
                                      lecture_id BIGINT NOT NULL,
                                      registration_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                      FOREIGN KEY (lecture_id) REFERENCES lecture(lecture_id)
);
INSERT INTO lecture (topic, description, instructor, lecture_status, created_at, updated_at, capacity)
VALUES
    ('Introduction to AI', 'Basic concepts and applications of Artificial Intelligence.', 'Dr. John Smith', 'PENDING', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 50),
    ('Advanced Java Programming', 'Deep dive into advanced Java programming techniques.', 'Prof. Jane Doe', 'ONGOING', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 40),
    ('Data Science with Python', 'Learn data analysis and visualization with Python.', 'Dr. Emily Davis', 'COMPLETED', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 35),
    ('Web Development Basics', 'Introduction to HTML, CSS, and JavaScript for beginners.', 'Mr. Robert Brown', 'PENDING', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 45),
    ('Machine Learning', 'An overview of machine learning algorithms and their applications.', 'Ms. Anna Johnson', 'ONGOING', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 30);

INSERT INTO lecture_application (user_id, lecture_id, created_at)
VALUES
    ('user1', 1, CURRENT_TIMESTAMP),
    ('user2', 2, CURRENT_TIMESTAMP),
    ('user3', 3, CURRENT_TIMESTAMP),
    ('user4', 4, CURRENT_TIMESTAMP),
    ('user5', 5, CURRENT_TIMESTAMP),
    ('user1', 2, CURRENT_TIMESTAMP),
    ('user2', 3, CURRENT_TIMESTAMP);



INSERT INTO lecture_registration (user_id, lecture_id, registration_date)
VALUES
    ('user1', 1, CURRENT_TIMESTAMP),
    ('user2', 2, CURRENT_TIMESTAMP),
    ('user3', 3, CURRENT_TIMESTAMP),
    ('user4', 4, CURRENT_TIMESTAMP),
    ('user5', 5, CURRENT_TIMESTAMP),
    ('user1', 2, CURRENT_TIMESTAMP),
    ('user2', 3, CURRENT_TIMESTAMP);
